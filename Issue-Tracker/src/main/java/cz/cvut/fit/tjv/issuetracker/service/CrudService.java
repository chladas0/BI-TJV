package cz.cvut.fit.tjv.issuetracker.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CrudService <ID, Entity, EntityDTO, EntityCreateDTO>{
    protected JpaRepository<Entity, ID> repository;

    public CrudService(JpaRepository<Entity, ID> repository) {
        this.repository = repository;
    }

    public EntityDTO create(EntityCreateDTO e) throws Exception {
       return toDTO(repository.save(toEntity(e)));
    }

    @Transactional
    public EntityDTO update(ID id, EntityCreateDTO e) throws Exception {
        Optional<Entity> optEntity = repository.findById(id);

        if (optEntity.isEmpty())
            throw new Exception("Fix me :)");

        return toDTO(repository.save(updateEntity(optEntity.get(), e)));
    }

    public List<EntityDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }

    public Optional<EntityDTO> findByIDasDTO(ID id)
    {
        return toDTO(repository.findById(id));
    }

    public List<Entity> findByIDs(List<ID> ids)
    {
        return repository.findAllById(ids);
    }

    public void deleteById(ID id) throws Exception {

        if (repository.findById(id).isEmpty())
            throw new Exception("entity not found Fix me :)");

        repository.deleteById(id);
    }


    protected abstract Entity updateEntity (Entity existingEntity, EntityCreateDTO e) throws Exception;
    protected abstract EntityDTO toDTO(Entity entity);
    private Optional<EntityDTO> toDTO(Optional<Entity> entity)
    {
        return entity.map(this::toDTO);
    }
    protected abstract Entity toEntity(EntityCreateDTO entityCreateDTO);
}
