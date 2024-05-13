package com.j2ee.gestionVente.restcontroller;

import com.j2ee.gestionVente.entities.User;
import com.j2ee.gestionVente.repository.UserRepository;
import com.j2ee.gestionVente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService ;

    @Autowired
    private UserRepository userRepository ;

    @PostMapping
    public User registerUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(value="/{id}")
    public User findAllUser(@PathVariable Long id){

        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> findAllUser(){

        return userService.getAllUsers() ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){

       return userService.loginUser(user) ;
    }

    @PostMapping("/logout")
    public void logoutAuth(@RequestBody User user ){

        userService.logoutUser(user) ;

    }

    //Admin , voir la liste des personnes connecté
    @GetMapping("/costumers/connected")
    public List<User> authUser(){

        return userService.authUsers() ;
    }

    @GetMapping("/costumers/disConnected")
    public List<User> notAuthUser(){

        return userService.notAuthUsers() ;
    }

    @GetMapping("/forgot/{email}")
    public User forgotPassword(@PathVariable("email") String email){

        return userService.forgotPassword(email) ;
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<User>> saveAllUsers(@RequestBody List<User> users)
    {
        return new ResponseEntity<>(userRepository.saveAll(users),HttpStatus.CREATED);
    }


    @PutMapping("/putAll")
    public ResponseEntity<List<User>> putAllUsers(@RequestBody List<User> users)
    {
        return new ResponseEntity<>(userRepository.saveAll(users),HttpStatus.CREATED);
    }

    @PutMapping("/articles")
    public User updateUserArticles(@RequestBody User user){

       return  userService.updateUserArticles(user) ;
    }

    @GetMapping("/saveArticle/{idUser}/{idArt}")
    public User updateUserArt(@PathVariable("idUser") Long id , @PathVariable Long idArt)
    {
        return  userService.updateArtUserAuth(id,idArt) ;
    }

    @DeleteMapping("/deleteArticle/{idUser}/{idArt}")
    public User deleteArtUser(@PathVariable("idUser") Long id , @PathVariable Long idArt)
    {
        return  userService.deleteArtUserAuth(id,idArt) ;
    }

    @DeleteMapping("/deleteAllArticles/{idUser}")
    public User deleteAllArticleByUser(@PathVariable("idUser") Long id)
    {

        return userService.deleteAllArticle(id) ;
    }

   @GetMapping(value="/total/{id}")
    public Float getTotalPriceArticle(@PathVariable("id") Long id)
   {

       return userService.getTotalArticlePrice(id) ;
   }




    //ADMIN afficher le nombre d'users par pages
    @GetMapping("/admin")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
}
