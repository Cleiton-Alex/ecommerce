package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.SalesmanDto;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.entities.User;
import projeto.ecommerce.enums.PerfilEnum;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.SalesmanService;
import projeto.ecommerce.services.UserService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salesman")
@CrossOrigin(origins = "*")
public class SalesmanController {

    private static final Logger log = LoggerFactory.getLogger(SalesmanController.class);

    @Autowired
    private SalesmanService salesmanService;

    @Autowired
    private UserService userService;

    public SalesmanController(){

    }




    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<SalesmanDto>> listaPorId(@PathVariable("id") Long id){
        log.info("Buscando Salesman por ID {}", id);
        Response<SalesmanDto> response = new Response<>();
        Optional<Salesman> salesman = this.salesmanService.buscarPorId(id);

        if(!salesman.isPresent()){
            log.info("Salesman não encontrado para o ID {}", id);
            response.getErrors().add("Salesman não encontrado para o id:" + id);
            return ResponseEntity.badRequest().body(response);

        }
        response.setData(this.converterSalesmanPraDTo(salesman.get()));
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/ativar/{id}")
    public ResponseEntity<Response<SalesmanDto>> ativarSalesman(@PathVariable("id") Long id){
        log.info("Buscando Salesman por ID {}", id);
        Response<SalesmanDto> response = new Response<>();
        Optional<Salesman> salesman = this.salesmanService.buscarPorId(id);
        Salesman salesmanAux = salesman.get();
        salesmanAux.setStatus(1);

        if(!salesman.isPresent()){
            log.info("Salesman não encontrado para o ID {}", id);
            response.getErrors().add("Salesman não encontrado para o id:" + id);
            return ResponseEntity.badRequest().body(response);

        }
        salesmanAux = this.salesmanService.pesistir(salesmanAux);
        response.setData(this.converterSalesmanPraDTo(salesmanAux));
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public List<SalesmanDto> listaSalesman(){
       return this.converterSalesmanPraDToList(this.salesmanService.buscarTodos());
    }


    @GetMapping(value = "/cpf")
    public ResponseEntity<Response<SalesmanDto>> listaPorCpf(@RequestParam(value ="cpf") String cpf){
        log.info("Buscando Salesman por CPF {}", cpf);
        Response<SalesmanDto> response = new Response<>();
        Optional<Salesman> salesman = this.salesmanService.buscarPorCpf(cpf);

        if(!salesman.isPresent()){
            log.info("Salesman não encontrado para CPF{}", cpf);
            response.getErrors().add("Salesman não encontrado para o CPF" + cpf);
            return ResponseEntity.badRequest().body(response);

        }
        response.setData(this.converterSalesmanPraDTo(salesman.get()));
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<Response<SalesmanDto>> cadastrar(@Validated @RequestBody SalesmanDto salesmanDto,
                                                           BindingResult result) throws ParseException{

        log.info("Cadastrando salesman: {}", salesmanDto.toString());
        Response<SalesmanDto> response = new Response<SalesmanDto>();
        User user = new User();
        user.setEmail(salesmanDto.getEmail());
        user.setSenha(salesmanDto.getSenha());
        user.setUserName(salesmanDto.getFullName());
        user.setPerfil(PerfilEnum.ROLE_USUARIO);
        user = this.userService.pesistir(user);
        salesmanDto.setUser(user);
        Salesman salesman = this.converterDtoParaSalesman(salesmanDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        salesman = this.salesmanService.pesistir(salesman);
        user.setSalesman(salesman);
        user = this.userService.pesistir(user);
        response.setData(this.converterSalesmanPraDTo(salesman));

        return ResponseEntity.ok(response);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<SalesmanDto>> atualizar(@PathVariable("id") Long id,
                                                           @Valid @RequestBody SalesmanDto salesmanDto, BindingResult result) throws ParseException {
        log.info("Atualizando lançamento: {}", salesmanDto.toString());
        Response<SalesmanDto> response = new Response<SalesmanDto>();
        salesmanDto.setId(Optional.of(id));
        User user = new User();
        user.setId(salesmanDto.getUser().getId());
        user.setEmail(salesmanDto.getUser().getEmail());
        user.setSenha(salesmanDto.getUser().getSenha());
        user.setUserName(salesmanDto.getFullName());
        user.setPerfil(salesmanDto.getUser().getPerfil());
        user = this.userService.pesistir(user);
        salesmanDto.setUser(user);
        Salesman salesman = this.converterDtoParaSalesman(salesmanDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        salesman = this.salesmanService.pesistir(salesman);
        user.setSalesman(salesman);
        user = this.userService.pesistir(user);
        response.setData(this.converterSalesmanPraDTo(salesman));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        Optional<Salesman> lancamento = this.salesmanService.buscarPorId(id);

        if (!lancamento.isPresent()) {
            log.info("Erro ao remover devido ao lançamento ID: {} ser inválido.", id);
            response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.salesmanService.remover(id);
        return ResponseEntity.ok(new Response<String>());
    }


    private Salesman converterDtoParaSalesman(SalesmanDto salesmanDto, BindingResult result) throws ParseException {

        Salesman salesman = new Salesman();
        salesman.setId(salesmanDto.getId().get());
        salesman.setFullName(salesmanDto.getFullName());
        salesman.setCpf(salesmanDto.getCpf());
        salesman.setOperation(salesmanDto.getOperation());
        salesman.setAccountType(salesmanDto.getAccountType());
        salesman.setPhoneNumber(salesmanDto.getPhoneNumber());
        salesman.setUser(salesmanDto.getUser());
        salesman.setBiography(salesmanDto.getBiography());
        salesman.setphoto(salesmanDto.getphoto());
        salesman.setStatus(0);
        salesman.setAgency(salesmanDto.getAgency());

        return salesman;

    }



    private SalesmanDto converterSalesmanPraDTo(Salesman salesman){

        SalesmanDto salesmanDto = new SalesmanDto();

        salesmanDto.setFullName(salesman.getFullName());
        salesmanDto.setCpf(salesman.getCpf());
        salesmanDto.setOperation(salesman.getOperation());
        salesmanDto.setAccountType(salesman.getAccountType());
        salesmanDto.setPhoneNumber(salesman.getPhoneNumber());
        salesmanDto.setUser(salesman.getUser());
        salesmanDto.setBiography(salesman.getBiography());
        salesmanDto.setphoto(salesman.getphoto());
        salesmanDto.setStatus(salesman.getStatus());
        salesmanDto.setAgency(salesman.getAgency());


        return salesmanDto;

    }
    private List<SalesmanDto> converterSalesmanPraDToList(List<Salesman> salesmens){

        List<SalesmanDto> salesmanDtolist = new ArrayList<>();
        for(Salesman salesman : salesmens){
            if(salesman.getUser()!=null) {

                SalesmanDto salesmanDto = new SalesmanDto();
                salesmanDto.setId(Optional.of(salesman.getId()));
                salesmanDto.setFullName(salesman.getFullName());
                salesmanDto.setCpf(salesman.getCpf());
                salesmanDto.setOperation(salesman.getOperation());
                salesmanDto.setAccountType(salesman.getAccountType());
                salesmanDto.setPhoneNumber(salesman.getPhoneNumber());
                salesmanDto.setEmail(salesman.getUser().getEmail());
                salesmanDto.setAgency(salesman.getAgency());
                salesmanDto.setBiography(salesman.getBiography());
                salesmanDto.setphoto(salesman.getphoto());
                salesmanDto.setStatus(salesman.getStatus());
                salesmanDto.setAgency(salesman.getAgency());
                salesmanDtolist.add(salesmanDto);
            }
        }
        return salesmanDtolist;

    }
}
