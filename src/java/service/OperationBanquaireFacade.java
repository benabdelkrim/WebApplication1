/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.OperationBanquaire;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class OperationBanquaireFacade extends AbstractFacade<OperationBanquaire> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<OperationBanquaire> findByCompte(Compte compte){
        return em.createQuery("select op from OperationBanquaire op where op.compte.id='"+compte.getId()+"'").getResultList();
    }
    
    public void createOperationDebit(Compte compte, double montant) {
        createOperation(compte, montant, 2);
    }

    public void createOperationCredit(Compte compte, double montant) {
        createOperation(compte, montant, 1);
    }

    public void createOperation(Compte compte, double montant, int type) {
        OperationBanquaire operationBanquaire = new OperationBanquaire();
        operationBanquaire.setMoutant(montant);
        operationBanquaire.setDateOperation(new Date());
        operationBanquaire.setCompte(compte);
        operationBanquaire.setType(type);
        create(operationBanquaire);
    }

    public OperationBanquaireFacade() {
        super(OperationBanquaire.class);
    }

}
