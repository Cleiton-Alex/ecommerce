package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.SalesmanDto;
import projeto.ecommerce.dtos.UserDto;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.enums.PerfilEnum;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.UserService;
import projeto.ecommerce.utils.PasswordUtils;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    public  UserController(){


    }


    @PostMapping
    public ResponseEntity<Response<UserDto>> cadastrar(@Validated @RequestBody UserDto userDto,
                                                       BindingResult result) throws ParseException{
        log.info("cadastrar user :{}", userDto.toString());
        Response<UserDto> response  = new Response<>();
        validarDadosExistentes(userDto, result);
        User user = this.converterDtoParaUser(userDto, result);

        if(result.hasErrors()){

            log.error("erro validando dados de cadastro user: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        user = this.userService.pesistir(user);
        response.setData(this.converterUserPraDTo(user));
        return ResponseEntity.ok(response);


    }

    private User converterDtoParaUser(UserDto userDto, BindingResult result) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPerfil(PerfilEnum.ROLE_USUARIO);
        user.setSenha(PasswordUtils.gerarBCrypt(userDto.getSenha()));

        return user;
    }


    private void validarUser(UserDto userDto, BindingResult result){
        if(userDto.getId() == null){
            result.addError(new ObjectError("user", "user não encontrado"));
            return;

        }

        log.info("validar user id: {}", userDto.getId());
        Optional<User> user = this.userService.buscarPorId(userDto.getId());
        if (!user.isPresent()){
            result.addError(new ObjectError("user", "user não encontrado. ID inexistente."));
        }
    }


    private void validarDadosExistentes(UserDto userDto, BindingResult result) {


        this.userService.buscarPorUser(userDto.getUserName())
                .ifPresent(func -> result.addError(new ObjectError("user", "user  já existente.")));


        this.userService.buscarPorEmail(userDto.getEmail())
                .ifPresent(func -> result.addError(new ObjectError("user", "Email já existente.")));

    }



    private UserDto converterUserPraDTo(User user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPerfil(user.getPerfil());
        userDto.setSenha(user.getSenha());
        userDto.setUserName(user.getUserName());
        return userDto;

    }
}
