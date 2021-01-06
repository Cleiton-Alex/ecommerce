package projeto.Ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.Ecommerce.dtos.MidiasDto;
import projeto.Ecommerce.entities.Salesman;
import projeto.Ecommerce.response.Response;
import projeto.Ecommerce.services.MidiasService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/midias")
@CrossOrigin(origins = "*")
public class MidiasController {
//    private static final Logger log = LoggerFactory.getLogger(MidiasController.class);
//    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Autowired
//    private MidiasService midiasService;
//
//    public MidiasController() {
//
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Response<MidiasDto>> adicionar(@Validated @RequestBody MidiasDto midiasDto,
//                                                         BindingResult result) throws ParseException {
//
//        log.info("Adicionando mídias: {} ", midiasDto.toString());
//        Response<MidiasDto> response = new Response<>();
//        validarMidias(midiasDto, result);
//        Salesman salesman = this.converterDtoParaMidia(midiasDto);
//
//        if (result.hasErrors()) {
//            log.error("Erro validando Midias: {}", result.getAllErrors());
//            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        salesman = this.midiasService.persiste(salesman);
//        response.setData(this.converterMidiasParaDTo(salesman));
//        return  ResponseEntity.ok(response);
//
//    }
//
//    private void validarMidias(MidiasDto midiasDto, BindingResult result) {
//        if (midiasDto.getNome().equals(null) || midiasDto.getNome() =="") {
//            result.addError(new ObjectError("mídias", "Nome da mídia não informado"));
//            return;
//        }
//
//
//        if (midiasDto.getDataUpload() == null || midiasDto.getDataUpload() == "") {
//            result.addError(new ObjectError("mídias", "Data da mídia não informado"));
//            return;
//        }
//
//
//        if (midiasDto.getDeletadoMidias() == null || midiasDto.getDeletadoMidias() == "") {
//            result.addError(new ObjectError("mídias", "Deleted da mídia não informado"));
//            return;
//        }
//
//    }
//
//
//    private MidiasDto converterMidiasParaDTo(Salesman salesman){
//
//        MidiasDto midiasDto = new MidiasDto();
//        midiasDto.setId(salesman.getId());
//        midiasDto.setNome(salesman.getNome());
//        midiasDto.setDeletadoMidias(salesman.getDeletadoMidias());
//        midiasDto.setDuracao(salesman.getDuracao());
//        midiasDto.setUrl(salesman.getUrl());
//        midiasDto.setDataUpload(salesman.getDataUpload() == null || midiasDto.getDataUpload() ==""
//                ? null : this.dateFormat.format(salesman.getDataUpload()));
//        return midiasDto;
//    }
//
//    private Salesman converterDtoParaMidia(MidiasDto midiasDto) throws ParseException {
//        Salesman salesman = new Salesman();
//        salesman.setNome(midiasDto.getNome());
//        salesman.setDuracao(midiasDto.getDuracao());
//        salesman.setDeletadoMidias(midiasDto.getDeletadoMidias());
//        salesman.setDataUpload(midiasDto.getDataUpload() =="" || midiasDto.getDataUpload() == null
//                ? null: this.dateFormat.parse(midiasDto.getDataUpload()) );
//        salesman.setUrl(midiasDto.getUrl());
//        return salesman;
//    }


}
