package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.service.CrudService;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CrudController <ID, Entity, DTO, CreateDTO>{
    protected final CrudService<ID, Entity, DTO, CreateDTO> crudService;

    public CrudController(CrudService<ID, Entity, DTO, CreateDTO> crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    protected List<DTO> getAll()
    {
        return crudService.findAll();
    }

    @GetMapping("/{id}")
    protected DTO getById(@PathVariable ID id){
        return crudService.findByIDasDTO(id).orElseThrow(()
                -> new EntityStateException("Entity not found"));
    }

    protected DTO create(@RequestBody CreateDTO createDTO){
        return crudService.create(createDTO);
    }

    @PutMapping("/{id}")
    protected DTO update (@PathVariable ID id, @RequestBody CreateDTO createDTO){
            return crudService.update(id, createDTO);
    }

    @DeleteMapping("/{id}")
    protected void delete(@PathVariable ID id){
            crudService.deleteById(id);
    }
}
