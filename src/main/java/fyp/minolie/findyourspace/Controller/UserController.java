package main.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)

    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
}
