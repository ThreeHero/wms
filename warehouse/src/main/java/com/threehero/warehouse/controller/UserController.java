package com.threehero.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.threehero.warehouse.common.Result;
import com.threehero.warehouse.dao.User;
import com.threehero.warehouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author threehero
 * @since 2022-11-14
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 用户登录
   * @param user
   * @return
   */
  @PostMapping("/login")
  public Result login(@RequestBody User user) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getNo, user.getNo());
    User one = userService.getOne(queryWrapper);

    if (one == null) {
      return Result.fail("登录失败, 未找到用户");
    }
    if (user.getPassword().equals(one.getPassword())) {
      // 密码匹配
      return Result.success("登陆成功", one);
    }

    return Result.fail("登录失败, 密码错误");
  }

  @PostMapping("/logout")
  public Result logout() {    return Result.success("退出登录成功");
  }

  // 增
  @PostMapping
  public Result save(@RequestBody User user) {
    userService.save(user);
    return Result.success("保存用户成功");
  }

  // 删
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Integer id) {
    userService.removeById(id);
    return Result.success("删除用户成功");
  }

  // 改
  @PutMapping
  public Result update(@RequestBody User user) {
    userService.updateById(user);
    return Result.success("修改用户成功");
  }

  /**
   * 按照id查询用户信息
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public Result getById(@PathVariable String id) {
    User user = userService.getById(id);
    if (user != null) {
      return Result.success("查询用户信息成功", user);
    }
    return Result.fail("没有找到用户信息");
  }

  // 查
  @GetMapping("/page")
  public Result page(Integer page, Integer pageSize, String name, Integer sex) {
    Page<User> pageInfo = new Page<>(page, pageSize);
    // 查询条件
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    // 模糊查询 查询名字
    queryWrapper.like(name != null, User::getName, name);

    queryWrapper.eq(sex != null, User::getSex, sex);
    userService.page(pageInfo, queryWrapper);

    return Result.success("查询用户列表成功", pageInfo);
  }
}
