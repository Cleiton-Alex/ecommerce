package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.CategoriesDto;
import projeto.ecommerce.entities.Categories;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.CategoriesService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {

    private static final Logger log = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private CategoriesService categoriesService;


    public CategoriesController(){

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<CategoriesDto>> buscarPorId(@PathVariable("id") Long id){

        log.info("Buscando por id: {}", id);
        Response<CategoriesDto> response = new Response<>();
        Optional<Categories> categories = categoriesService.buscarPorId(id);

        if (!categories.isPresent()){
            log.info("Categories não encontrada para o ID: {}", id);
            response.getErrors().add("Categories não encontrada para o ID:" + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.converterCategoriesPraDTo(categories.get()));
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Response<CategoriesDto>> buscarPorName(@PathVariable("name") String name){

        log.info("Buscando por id: {}", name);
        Response<CategoriesDto> response = new Response<>();
        Optional<Categories> categories = categoriesService.buscarPorName(name);

        if (!categories.isPresent()){
            log.info("Categories não encontrada para o Name: {}", name);
            response.getErrors().add("Categories não encontrada para o name" + name);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.converterCategoriesPraDTo(categories.get()));
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public List<Categories> listaSalesman(){
        return this.categoriesService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Response<CategoriesDto>> cadastrar(@Validated @RequestBody CategoriesDto categoriesDto,
                                                           BindingResult result) throws ParseException{

        log.info("Cadastrando salesman: {}", categoriesDto.toString());
        Response<CategoriesDto> response = new Response<CategoriesDto>();
        Categories categories = this.converterDtoParaCategories(categoriesDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        categories = this.categoriesService.pesistir(categories);
        response.setData(this.converterCategoriesPraDTo(categories));
        return ResponseEntity.ok(response);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<CategoriesDto>> atualizar(@PathVariable("id") Long id,
                                                             @Valid @RequestBody CategoriesDto categoriesDto, BindingResult result) throws ParseException {
        log.info("Atualizando lançamento: {}", categoriesDto.toString());
        Response<CategoriesDto> response = new Response<CategoriesDto>();
        categoriesDto.setId(id);
        Categories categories = this.converterDtoParaCategories(categoriesDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        categories = this.categoriesService.pesistir(categories);
        response.setData(this.converterCategoriesPraDTo(categories));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        Optional<Categories> lancamento = this.categoriesService.buscarPorId(id);

        if (!lancamento.isPresent()) {
            log.info("Erro ao remover devido ao lançamento ID: {} ser inválido.", id);
            response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.categoriesService.remover(id);
        return ResponseEntity.ok(new Response<String>());
    }


    private Categories converterDtoParaCategories(CategoriesDto categoriesDto, BindingResult result) throws ParseException {

        Categories categories = new Categories();

        categories.setId(categoriesDto.getId());
        categories.setName(categoriesDto.getName());

        return categories;

    }



    private CategoriesDto converterCategoriesPraDTo(Categories categories){

        CategoriesDto categoriesDto = new CategoriesDto();

        categoriesDto.setId(categories.getId());
        categoriesDto.setName(categories.getName());

        return categoriesDto;

    }
}
