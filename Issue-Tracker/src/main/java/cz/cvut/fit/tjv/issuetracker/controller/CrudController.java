package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.service.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public DTO getById(@PathVariable ID id) throws Exception {
        Optional<DTO> entity = crudService.findByIDasDTO(id);
        if(entity.isEmpty())
            throw new Exception("Non existing entity - FIX ME :) ");

        return entity.get();
    }

    @PostMapping
    public DTO create(@RequestBody CreateDTO createDTO) throws Exception {
       return crudService.create(createDTO);
    }

    @PutMapping("/{id}")
    DTO update (@PathVariable ID id, @RequestBody CreateDTO createDTO) throws Exception {
        return crudService.update(id, createDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) throws Exception {
        crudService.deleteById(id);
    }
}
