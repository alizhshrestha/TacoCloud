package com.example.tacocloud.controller;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.repo.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8090")
@Slf4j
public class TacoController {

    private TacoRepository tacoRepo;
    private RestTemplate restTemplate;

    public TacoController(TacoRepository tacoRepo,
                          RestTemplate restTemplate) {
        this.tacoRepo = tacoRepo;
        this.restTemplate = restTemplate;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending()
        );
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);

        if(optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepo.save(taco);
    }

//    @PutMapping(path = "/{orderId}", consumes = "application/json")
//    public TacoOrder putOrder(
//            @PathVariable("orderId") Long orderId,
//            @RequestBody TacoOrder order){
//        order.setId(orderId);
//        return repo.save(order);
//    }

//    @PatchMapping(path = "/{orderId}", consumes = "application/json")
//    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
//                                @RequestBody TacoOrder patch){
//        TacoOrder order = repo.findById(orderId).get();
//
//        if (patch.getDeliveryName() != null) {
//            order.setDeliveryName(patch.getDeliveryName());
//        }
//        if (patch.getDeliveryStreet() != null) {
//            order.setDeliveryStreet(patch.getDeliveryStreet());
//        }
//        if (patch.getDeliveryCity() != null) {
//            order.setDeliveryCity(patch.getDeliveryCity());
//        }
//        if (patch.getDeliveryState() != null) {
//            order.setDeliveryState(patch.getDeliveryState());
//        }
//        if (patch.getDeliveryZip() != null) {
//            order.setDeliveryZip(patch.getDeliveryZip());
//        }
//        if (patch.getCcNumber() != null) {
//            order.setCcNumber(patch.getCcNumber());
//        }
//        if (patch.getCcExpiration() != null) {
//            order.setCcExpiration(patch.getCcExpiration());
//        }
//        if (patch.getCcCVV() != null) {
//            order.setCcCVV(patch.getCcCVV());
//        }
//
//        return repo.save(order);
//    }

//    @DeleteMapping("/{orderId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteOrder(@PathVariable("orderId") Long orderId){
//        try{
//            repo.deleteById(orderId);
//        }catch (EmptyResultDataAccessException e){
//
//        }
//    }

    /* RestTemplate example */
//    public Ingredient getIngredientById(String ingredientId){
///*        return restTemplate.getForObject("http://localhost:8090/ingredients/{id}",
//                Ingredient.class, ingredientId);*/
//        //next method
///*        Map<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id", ingredientId);
//        return restTemplate.getForObject("http://localhost:8090/ingredients/{id}",
//                Ingredient.class, urlVariables);*/
//
//        //next method
///*        Map<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id", ingredientId);
//        URI url = UriComponentsBuilder
//                .fromHttpUrl("http://localhost:8090/ingredients/{id}")
//                .build(urlVariables);
//        return restTemplate.getForObject(url, Ingredient.class);*/
//    }

//    public Ingredient getIngredientById(String ingredientId){
//        ResponseEntity<Ingredient> responseEntity =
//                restTemplate.getForEntity("http://localhost:8090/ingredients/{id}",
//                        Ingredient.class, ingredientId);
//        log.info("Fetched time: {}",
//                responseEntity.getHeaders().getDate());
//        return responseEntity.getBody();
//    }

//    /* To update ingredient with RestTemplate */
//    public void updateIngredient(Ingredient ingredient){
//        restTemplate.put("http://localhost:8090/ingredients/{id}",
//                ingredient, ingredient.getId());
//    }


//    /* To delete ingredient with RestTemplate */
//    public void deleteIngredient(Ingredient ingredient){
//        restTemplate.delete("http://localhost:8090/ingredients/{id}", ingredient.getId());
//    }

//    public Ingredient createIngredient(Ingredient ingredient){
//        return restTemplate.postForObject("http://localhost:8090/ingredients/{id}",
//                ingredient, Ingredient.class);
//    }

}
