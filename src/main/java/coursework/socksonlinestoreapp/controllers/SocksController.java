package coursework.socksonlinestoreapp.controllers;

import coursework.socksonlinestoreapp.model.Socks;
import coursework.socksonlinestoreapp.model.Color;
import coursework.socksonlinestoreapp.model.Size;
import coursework.socksonlinestoreapp.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков на складе")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос успешно выполнен"),
        @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
        @ApiResponse(responseCode = "500", description = "Что-то пошло не так")
})
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @Operation(
            summary = "Регистрация прихода носков на склад"
    )
    @PostMapping
    public ResponseEntity<Socks> addSocks(@RequestParam Color color,
                                          @RequestParam Size size,
                                          @RequestParam int cottonPart,
                                          @RequestParam int quantity) {
        if(cottonPart < 0 || cottonPart > 100 || quantity <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Socks addedSocks = socksService.addSocks(new Socks(color, size, cottonPart), quantity);
        return ResponseEntity.ok().body(addedSocks);
    }

    @Operation(
            summary = "Выпуск носков со склада"
    )
    @PutMapping
    public ResponseEntity<Socks> releaseSocks(@RequestParam Color color,
                                              @RequestParam Size size,
                                              @RequestParam int cottonPart,
                                              @RequestParam int quantity) {
        if(cottonPart < 0 || cottonPart > 100 || quantity <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Socks addedSocks = socksService.releaseSocks(new Socks(color, size, cottonPart), quantity);
        if(addedSocks == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().body(addedSocks);

    }

    @Operation(
            summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса"
    )
    @GetMapping
    public ResponseEntity<Integer> getQuantity(@RequestParam Color color,
                                               @RequestParam Size size,
                                               @RequestParam(required = false) Integer cottonMin,
                                               @RequestParam(required = false) Integer cottonMax) {
        if(cottonMax < 0 || cottonMax > 100 || cottonMin < 0 || cottonMin > 100){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(socksService.getQuantity(color, size, cottonMin, cottonMax));
    }

    @Operation(
            summary = "Списание бракованного носков"
    )
    @DeleteMapping
    public ResponseEntity<Void> writeOffSocks(@RequestParam Color color,
                                              @RequestParam Size size,
                                              @RequestParam int cottonPart,
                                              @RequestParam int quantity) {
        if(cottonPart < 0 || cottonPart > 100 || quantity <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (socksService.writeOffSocks(new Socks(color, size, cottonPart), quantity)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
