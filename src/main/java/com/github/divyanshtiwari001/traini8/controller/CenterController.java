package com.github.divyanshtiwari001.traini8.controller;



import java.util.List;
import java.util.stream.Collectors;

import com.github.divyanshtiwari001.traini8.assembler.CenterModelAssembler;
import com.github.divyanshtiwari001.traini8.model.Center;
import com.github.divyanshtiwari001.traini8.response.Response;
import com.github.divyanshtiwari001.traini8.service.CenterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



@RestController
@RequestMapping("/api/v1")
@Tag(name = "Center", description = "Center API")
public class CenterController {
    private CenterService service;
    private CenterModelAssembler assembler;

    public CenterController(CenterService service, CenterModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // Aggregate root
    @GetMapping("/centers")
    @Operation(summary = "Get all centers", description = "Get all centers with optional filters - state, city, pincode, centername and centercode. Pagination is supported.")
    public ResponseEntity<Response> all(
        @RequestParam(value = "state", required = false) String state,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "pincode", required = false) String pincode,
        @RequestParam(value = "centername", required = false) String centerName,
        @RequestParam(value = "centercode", required = false) String centerCode,
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Center> centersPage = service.getFilteredCenters(state,city,pincode,centerName,centerCode,pageable);

        List<EntityModel<Center>> centerList =  centersPage.getContent().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()); 

        CollectionModel<EntityModel<Center>> centers = CollectionModel.of(centerList,
                linkTo(methodOn(CenterController.class).all(state,city,pincode,centerName,centerCode,page,size)).withSelfRel());

        addPaginationLinks(centersPage, centers);

        return ResponseEntity.ok().body(new Response("centers found", 200, centers));
        
    }

    private void addPaginationLinks(Page<Center> centersPage, CollectionModel<EntityModel<Center>> centerModels) {
        int currentPage = centersPage.getPageable().getPageNumber();
        int pageSize = centersPage.getPageable().getPageSize();

        if (centersPage.hasNext()) {
            centerModels.add(linkTo(methodOn(CenterController.class).all(
                    null, null, null, null, null,currentPage+1,pageSize))
                    .withRel("next"));
        }
        if (centersPage.hasPrevious()) {
            centerModels.add(linkTo(methodOn(CenterController.class).all(
                    null, null, null, null, null,currentPage - 1, pageSize))
                    .withRel("prev"));
        }
        if (centersPage.getTotalPages() > 1) {
            centerModels.add(linkTo(methodOn(CenterController.class).all(
                    null, null, null, null, null, centersPage.getTotalPages() - 1, pageSize))
                    .withRel("last"));
        }
    }


    // single
    @GetMapping("/centers/{id}")
    @Operation(summary = "Get center by id", description = "Get center as per id provided")
    public ResponseEntity<Response> one(@PathVariable Long id) {
        Center center = service.getCenterById(id);

        EntityModel<Center> model = assembler.toModel(center);

        return ResponseEntity.ok(new Response("center found", 200, model));
    }

    @PostMapping("/centers")
    @Operation(summary = "Create new center", description = "Add new center to the system")
    public ResponseEntity<Response> newCenter(@Valid @RequestBody Center center) {
        EntityModel<Center> model = assembler.toModel(service.newCenter(center));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(new Response("center created", 201, model));
    }  
}
