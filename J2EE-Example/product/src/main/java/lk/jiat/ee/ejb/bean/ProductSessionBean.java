package lk.jiat.ee.ejb.bean;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lk.jiat.ee.core.exception.InvalidParaException;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.service.ProductService;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Stateless

public class ProductSessionBean implements ProductService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<Product> getProductById(long id) {
//       Product product = em.find(Product.class, id);
//        return product;
        return Optional.ofNullable(em.find(Product.class, id));
    }


    @Override
    public Optional<Product> getProductByName(String name) {
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.FindByName", Product.class)
                    .setParameter("name", name);

            return Optional.ofNullable(query.getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> product = em.createNamedQuery("Product.FindByCategory", Product.class)
                .setParameter("category", category)
                .getResultList();
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> product = em.createNamedQuery("Product.FindAll", Product.class)
                .getResultList();
        return product;

    }

    @Override
    public void addProduct(Product product) {
        em.persist(product);

    }

    @Override
    public void updateProduct(Product product) {

        em.merge(product);

    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteProduct(long id) {

        if(id < 0){
            throw new InvalidParaException("Invalid product id");
        };
        Optional<Product> product = getProductById(id);
        em.remove(product);

    }
}
