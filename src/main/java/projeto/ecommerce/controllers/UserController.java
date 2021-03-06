package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.UserDto;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.enums.PerfilEnum;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.UserService;
import projeto.ecommerce.utils.PasswordUtils;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<UserDto>> listaPorId(@PathVariable("id") Long id){
        log.info("Buscando usuarios por ID {}", id);
        Response<UserDto> response = new Response<>();
        Optional<User> user = this.userService.buscarPorId(id);

        if(!user.isPresent()){
            log.info("usuarios não encontrado para o ID {}", id);
            response.getErrors().add("usuario não encontrado para o id" + id);
            return ResponseEntity.badRequest().body(response);

        }
        response.setData(this.converterUserPraDTo(user.get()));
        return ResponseEntity.ok(response);
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<UserDto>> atualizar(@PathVariable("id") Long id,
                                                           @Valid @RequestBody UserDto userDto, BindingResult result) throws ParseException {
        log.info("Atualizando lançamento: {}", userDto.toString());
        Response<UserDto> response = new Response<UserDto>();
        userDto.setId(id);
        User user = this.converterDtoParaUser(userDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        user = this.userService.pesistir(user);
        response.setData(this.converterUserPraDTo(user));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        Optional<User> lancamento = this.userService.buscarPorId(id);

        if (!lancamento.isPresent()) {
            log.info("Erro ao remover devido ao lançamento ID: {} ser inválido.", id);
            response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.userService.remover(id);
        return ResponseEntity.ok(new Response<String>());
    }


    @GetMapping
    public List<User> listaSalesman(){
        return this.userService.buscarTodos();
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
        if(user.getSalesman()!= null) {
            userDto.setSalesman_id(user.getSalesman().getId());
        }
        return userDto;

    }
}
