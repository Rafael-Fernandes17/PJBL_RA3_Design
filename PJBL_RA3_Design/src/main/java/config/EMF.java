package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
        private static EMF instancia;

        //gera a instacia da fabrica
        private EntityManagerFactory emf;

        private EMF() {
            emf = Persistence.createEntityManagerFactory("SistemaFaculdade");
        }

        public static EMF getInstancia() {
            if (instancia == null) {
                instancia = new EMF();
            }

            return instancia;
        }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager gerarEntityMenager(){
            return emf.createEntityManager();
    }

    public void fechar() {
            emf.close();
        }
}
