package com.weizhang.controller.user;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizhang.common.R;
import com.weizhang.controller.BaseController;
import com.weizhang.form.FormUser;
import com.weizhang.model.Role;
import com.weizhang.model.User;
import com.weizhang.service.RoleService;
import com.weizhang.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "username", defaultValue = "" ) String username,
                             @RequestParam(value = "start", defaultValue = "") String start,
                             @RequestParam(value = "end", defaultValue = "") String end
                             ) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("start", start);
        params.put("end", end);

        PageHelper.startPage(page, PAGESIZE);
        List<User> list = userService.getList();
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("params", params);

        modelAndView.setViewName("admin/user/list");
        modelAndView.addObject("list", pageInfo);

        return modelAndView;
    }


    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam("id") int id) throws Exception {

        User user = userService.getRow(id);
        if(user == null){
            throw new Exception("Not Found");
        }


        List<User> userRoles = userService.getUserRoleList(id);
        List<Role> roles = roleService.getList();

        List<Object> checkboxList = new ArrayList<>();

        for (Role role : roles) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            map.put("checked", false);

            for (User userRole : userRoles) {
                if(role.getId() == userRole.getRoleId()){
                    map.put("checked", true);
                }
            }

            checkboxList.add(map);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("checkboxList", checkboxList);

        modelAndView.setViewName("admin/user/index");
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView add(){
        List<Role> roles = roleService.getList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles",roles);
        modelAndView.setViewName("admin/user/add");
        return modelAndView;
    }

    /**
     * 增加
     * @return
     */
    @PostMapping(value = "doadd")
    @ResponseBody
    public R doAdd(@Validated FormUser userForm,
                   BindingResult bindingResult,
                   @RequestParam(name = "roles") String roles,
                   @RequestParam(name = "pass") String password){
        if(bindingResult.hasErrors()){
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        //写入用户
        User user = new User();
        user.setName(userForm.getName());
        user.setPassword( BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setCratedAt(new Date());
        user.setStatus(1);
        int id = userService.inser(user);
        if(id == 0){
            return R.error();
        }
        JSONArray jsonArray = JSONObject.parseArray(roles);
        for (int i = 0; i < jsonArray.size(); i++){
            userService.insertMiddleData( user.getId(), jsonArray.getIntValue(i));
        }
        return R.success();

    }

    /**
     * 修改
     * @return
     */
    @PostMapping("modify")
    @ResponseBody
    public R modify(@RequestParam("id") int id,
                    @RequestParam("pass") String password,
                    @RequestParam(name = "roles") String roles,
                    @RequestParam("newpassword") String newpassword){

        User user = userService.getRow(id);

       if(false ==  BCrypt.checkpw(password, user.getPassword())){
           return R.error("旧密码错误");
       }


        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        if(userService.update(user) < 1){
            return R.error();
        }

        //批量删除之前绑定的
        userService.deleteMiddleData(user.getId());

        JSONArray jsonArray = JSONObject.parseArray(roles);
        for (int i = 0; i < jsonArray.size(); i++){
            userService.insertMiddleData( user.getId(), jsonArray.getIntValue(i));
        }

        return R.success();
    }

}
