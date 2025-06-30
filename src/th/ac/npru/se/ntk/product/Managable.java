package th.ac.npru.se.ntk.product;

/**
 * The {@code Managable} interface defines the standard operation for managing entities,
 * such as deleting by a specific identifier.
 * 
 * Classes that implement this interface should provide logic for deleting an entity 
 * (such as a product or user) by its integer ID.
 * 
 * This interface is typically used in systems that require basic CRUD operations.
 * 
 * @author 
 */
public interface Managable {

    /**
     * Deletes the entity identified by the given ID.
     * 
     * @param id the unique identifier of the entity to be deleted
     * @return {@code true} if the deletion was successful; {@code false} otherwise
     */
    public boolean deleteById(int id);
}
