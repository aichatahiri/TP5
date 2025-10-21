package test;

import entities.Category;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

public class TestCategory {

    @Test
    public void testCreateCategoryAndProduct() {
        // Ouvrir une session Hibernate
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Création d’une catégorie
            Category c1 = new Category();
            c1.setName("Informatique");

            // Création d’un produit lié à la catégorie
            Product p1 = new Product();
            p1.setName("Laptop Dell XPS");
            p1.setDescription("Ordinateur portable 16 Go RAM, 512 Go SSD");
            p1.setPrice(1300.0); // utiliser double pour le prix
            p1.setCategory(c1);

            // Sauvegarde dans la base
            session.save(c1);
            session.save(p1);

            tx.commit();

            // Vérifications automatiques
            assertNotNull(c1.getId(), "L’ID de la catégorie ne doit pas être null");
            assertNotNull(p1.getId(), "L’ID du produit ne doit pas être null");
            assertEquals("Informatique", c1.getName(), "Le nom de la catégorie doit être correct");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail("Exception lors du test : " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }
}
