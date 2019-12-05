package com.jurecki.repository;

import com.jurecki.model.Game;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author Dawid Jurecki on 02.12.2019
 **/

@Component
public class GamesRepository {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void persistGame(Game game){
        if(game.getPublisher().equals("") || game.getTitle().equals("")){
            return;
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(game);
            tx.commit();
        } catch (HibernateException e){
            if(tx != null) tx.rollback();
        } finally{
            session.close();
        }
    }

    public Game getGameById(int id){
        Session session = factory.openSession();
        Game games = (Game) session.createQuery("FROM com.jurecki.model.Game WHERE id = " + id).uniqueResult();
        session.close();
        return games;
    }

    public List<Game> getGameList(){
        Session session = factory.openSession();
        List<Game> gamesList = session.createQuery("FROM com.jurecki.model.Game").list();
        session.close();
        return gamesList;
    }

    public void updateGame(Game updatedGame) {
        if(getGameById(updatedGame.getId())==null){
            return;
        }
        Game game = getGameById(updatedGame.getId());
        if(updatedGame.getTitle()==null){
            game.setPublisher(updatedGame.getPublisher());
        }
        if(updatedGame.getPublisher()==null){
            game.setTitle(updatedGame.getTitle());
        }

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(game);
            tx.commit();
        } catch (HibernateException e){
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }


    public void deleteGame(int id) {
        if(getGameById(id)==null){
            return;
        }
        Game game = getGameById(id);
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(game);
            tx.commit();
        } catch (HibernateException e){
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}

/*

    public void updateGame(int id) {
        Game game = getGameById(id);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update title: ");
        game.setTitle(scanner.nextLine());
        System.out.println("Update publisher: ");
        game.setPublisher(scanner.nextLine());

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(game);
            tx.commit();
        } catch (HibernateException e){
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
*/
