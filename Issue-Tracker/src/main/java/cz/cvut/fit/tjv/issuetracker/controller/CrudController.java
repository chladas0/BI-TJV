package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.service.CrudService;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CrudController <ID, Entity, DTO, CreateDTO>{
    protected final CrudService<ID, Entity, DTO, CreateDTO> crudService;

    public CrudController(CrudService<ID, Entity, DTO, CreateDTO> crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    public List<DTO> getAll()
    {
        return crudService.findAll();
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable ID id){
        return crudService.findByIDasDTO(id).orElseThrow(()
                -> new EntityStateException("Entity not found"));
    }

    public DTO create(@RequestBody @Valid CreateDTO createDTO){
        return crudService.create(createDTO);
    }

    @PutMapping("/{id}")
    public DTO update (@PathVariable ID id, @RequestBody @Valid CreateDTO createDTO){
            return crudService.update(id, createDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id){
            crudService.deleteById(id);
    }
}
