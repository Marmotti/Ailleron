package warsztaty.spring.ailleron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsztaty.spring.ailleron.exception.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.model.UserList;
import warsztaty.spring.ailleron.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/*
*Jesli mapujemy zmienna o innej nazwie niz ta uzyta w funkcji, to po @PathVariable musimy dać ("to co w mapping)
*/
@RestController
@RequestMapping("/users")
public class UserController {

/*
* Adnotacje przy Beanie
* Ogólnie to Autowired wyszukuje po nazwie klasy i wstzrykuje kiedy jest potrzebne
* Jest też Primary co bierze pierwszy (jak jest kilka pasujących klas)
* Polecają dawać w konstruktorze!, bo jest lepsza widoczność
* Jesli robimy przez konstruktor, to nie potrzebujemy żadnych adnotacji @Autowired
* Innym mechanizmem są Conditionale
* decydujemy jakie beany trzeba podnosić a jakie nie, z podejscia bardziej niskopoziomowego
*/
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/{name}", headers = "X-API-VERSION=2")
    public User getSurnameByName(@PathVariable String name){
        Optional<User> userByName = userService.getUserByName(name);
        if (userByName.isPresent()){
            return userByName.get();
        }
        throw  new UserNotFoundException("User with name: " + name + " was not found.");
    }

    @GetMapping
    public UserList getUsers(){
        return new UserList(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyUser(@RequestBody @Valid User user, @PathVariable Long id){
        userService.modifyUser(user, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
