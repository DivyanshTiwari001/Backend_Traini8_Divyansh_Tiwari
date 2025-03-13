package com.github.divyanshtiwari001.traini8.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.github.divyanshtiwari001.traini8.model.Center;
import com.github.divyanshtiwari001.traini8.controller.CenterController;

@Component
public class CenterModelAssembler implements RepresentationModelAssembler<Center, EntityModel<Center>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Center> toModel(Center center) {
       EntityModel<Center> centerModel = EntityModel.of(center
       , linkTo(methodOn(CenterController.class).one(center.getId())).withSelfRel().withType("GET")
       , linkTo(methodOn(CenterController.class).all(center.getCenterAddress().getState(),
       center.getCenterAddress().getCity(),center.getCenterAddress().getPincode(),center.getCenterName(),center.getCenterCode()
       ,0,10)).withRel("centers").withType("GET")
       , linkTo(methodOn(CenterController.class).newCenter(center)).withRel("centers").withType("POST")
       );
        return centerModel;
    }
    
}