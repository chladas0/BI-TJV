package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CrudService <ID, Entity, DTO, CreateDTO>{
    protected JpaRepository<Entity, ID> repository;

    public CrudService(JpaRepository<Entity, ID> repository) {
        this.repository = repository;
    }


    @Transactional
    public DTO create(CreateDTO e) {
       return toDTO(repository.save(toEntity(e)));
    }


    @Transactional
    public DTO update(ID id, CreateDTO e) {
        Entity entity = repository.findById(id).orElseThrow(() -> new EntityStateException("Entity for updating not found"));

        return toDTO(repository.save(updateEntity(entity, e)));
    }


    public List<DTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }


    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }


    public Optional<DTO> findByIDasDTO(ID id)
    {
        return toDTO(repository.findById(id));
    }


    public List<Entity> findByIDs(List<ID> ids)
    {
        return repository.findAllById(ids);
    }


    public void deleteById(ID id) throws EntityStateException {

        if (repository.findById(id).isEmpty())
            throw new EntityStateException("Entity for deleting not found");

        repository.deleteById(id);
    }


    protected abstract Entity updateEntity (Entity existingEntity, CreateDTO e) throws EntityStateException;
    protected abstract DTO toDTO(Entity entity);
    private Optional<DTO> toDTO(Optional<Entity> entity)
    {
        return entity.map(this::toDTO);
    }
    protected abstract Entity toEntity(CreateDTO entityCreateDTO);
}
