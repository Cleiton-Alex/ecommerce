package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.ProductsDto;
import projeto.ecommerce.dtos.SalesmanDto;
import projeto.ecommerce.entities.Products;
import projeto.ecommerce.entities.Salesman;
import projeto.ecommerce.enums.PerfilEnum;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.SalesmanService;
import projeto.ecommerce.utils.PasswordUtils;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/salesman")
@CrossOrigin(origins = "*")
public class SalesmanController {

    private static final Logger log = LoggerFactory.getLogger(SalesmanController.class);

    @Autowired
    private SalesmanService salesmanService;


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
        Salesman salesman = this.converterDtoParaSalesman(salesmanDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        salesman = this.salesmanService.pesistir(salesman);
        response.setData(this.converterSalesmanPraDTo(salesman));
        return ResponseEntity.ok(response);

    }



    private Salesman converterDtoParaSalesman(SalesmanDto salesmanDto, BindingResult result) throws ParseException {

        Salesman salesman = new Salesman();

        salesman.setFullName(salesmanDto.getFullName());
        salesman.setCpf(salesmanDto.getCpf());
        salesman.setOperation(salesmanDto.getOperation());
        salesman.setAccountType(salesmanDto.getAccountType());
        salesman.setPhoneNumber(salesmanDto.getPhoneNumber());

        salesman.setUser(salesmanDto.getUser());
        salesman.setBiography(salesmanDto.getBiography());
        salesman.setSelfPortrait(salesmanDto.getSelfPortrait());
        salesman.setStatus(salesmanDto.getStatus());

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
        salesmanDto.setSelfPortrait(salesman.getSelfPortrait());
        salesmanDto.setStatus(salesman.getStatus());

        return salesmanDto;

    }
}
