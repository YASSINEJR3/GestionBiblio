package ma.fstm.ilisi2.projects.Controller;

import ma.fstm.ilisi2.projects.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.Model.bo.Emprunt;
import ma.fstm.ilisi2.projects.Model.bo.Livre;
import ma.fstm.ilisi2.projects.Model.service.ServiceAdherent;
import ma.fstm.ilisi2.projects.Model.service.ServiceEmprunt;
import ma.fstm.ilisi2.projects.Model.service.ServiceLivre;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Bibliotheque", value = {"/CrudLivre","/CrudAdherent","/CrudEmprunt","/AdminLogin"})
public class Bibliotheque extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if(path.equals("/AdminLogin")){
            String logOut = request.getParameter("logOut");
            if(logOut != null) {
                System.out.println(logOut);
                request.getSession().removeAttribute("admin");
                response.sendRedirect("CrudLivre");
            }else{
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            }
        }else if(path.equals("/CrudLivre")){
            ArrayList<Livre> livres = ServiceLivre.getLivres();
            if(livres.size() == 0)
                request.setAttribute("noSuchBook", "There is no such book!!!!!!!");
            request.setAttribute("livres", livres);
            request.getRequestDispatcher("/WEB-INF/GestionLivre/pagePrincipale.jsp").forward(request, response);
        }else if(path.equals("/CrudAdherent")){
            ArrayList<Adherent> adherents = ServiceAdherent.getAdherents();
            request.setAttribute("adherents",adherents);
            if(adherents.size() == 0)
                request.setAttribute("noSuchAdh", "There is no such Adh!!!!!!!");
            request.getRequestDispatcher("/WEB-INF/GestionAdherent/pagePrincipale.jsp").forward(request,response);

        }else if(path.equals("/CrudEmprunt")){
            ArrayList<Emprunt> emprunts = ServiceEmprunt.getEmprunts();
            request.setAttribute("emprunts",emprunts);
            if(emprunts.size() == 0)
                request.setAttribute("noSuchEmp", "There is no such Emprunt!!!!!!!");

            request.getRequestDispatcher("/WEB-INF/GestionEmprunt/pagePrincipale.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        if(path.equals("/AdminLogin")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(password);
            if(password.equals("123456789")){
                request.getSession().setAttribute("admin",username);
                response.sendRedirect("CrudLivre");
            }else{
                //error
                request.setAttribute("login_error","Votre username ou mot de passe n'est pas correct");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
            }
        }else if(path.equals("/CrudLivre")){
            String methode = request.getParameter("_method");
            if ((methode != null) && (methode.equals("delete"))) {

                String isbn = request.getParameter("d_isbn");
                ServiceLivre.supprimerLivre(isbn);
                response.sendRedirect("CrudLivre");

            }else if ((methode != null) && (methode.equals("put"))) {

                String isbn   = request.getParameter("m_isbn");
                String title  = request.getParameter("m_titre");
                String auteur = request.getParameter("m_auteur");
                Integer nbrexemp = Integer.valueOf(request.getParameter("m_nbrexemp"));

                ServiceLivre.modifierLivre(isbn,title,auteur,nbrexemp);
                response.sendRedirect("CrudLivre");

            }else if((methode != null) && (methode.equals("search"))){
                String s_auteur = request.getParameter("s_auteur");
                ArrayList<Livre> livres = ServiceLivre.getAuteurLivres(s_auteur);
                request.setAttribute("livres", livres);
                request.getRequestDispatcher("/WEB-INF/GestionLivre/pagePrincipale.jsp").forward(request, response);
            }else {
                String isbn = request.getParameter("isbn");
                String title = request.getParameter("title");
                String auteur = request.getParameter("auteur");
                Integer nbrexemp = Integer.valueOf(request.getParameter("nbrexemp"));

                if(ServiceLivre.ajouterLivre(isbn,title,auteur,nbrexemp)) {
                    response.sendRedirect("CrudLivre");
                }else{
                    request.setAttribute("add_error","Un livre déja existe avec cet "+isbn);
                    request.getRequestDispatcher("/WEB-INF/GestionLivre/addLivre.jsp").forward(request, response);
                }
            }
        }else if(path.equals("/CrudAdherent")){
            String methode = request.getParameter("_method");
            //Removing delete error
            request.getSession().removeAttribute("delete_Adh_error");
            if ((methode != null) && (methode.equals("delete"))) {
                Integer idclient = Integer.parseInt(request.getParameter("d_idClient"));
                if(!ServiceAdherent.supprimerAdherent(new Adherent(idclient)))
                    request.getSession().setAttribute("delete_Adh_error","Adherent "+idclient+" possède des emprunts!!!!!");
                response.sendRedirect("CrudAdherent");
            }else if ((methode != null) && (methode.equals("put"))) {
                Integer idClient = Integer.parseInt(request.getParameter("m_idClient"));
                String nom = request.getParameter("m_nom");
                String prenom = request.getParameter("m_prenom");
                String cin = request.getParameter("m_cin");

                ServiceAdherent.modifierAdherent(new Adherent(idClient,nom,prenom,cin));
                response.sendRedirect("CrudAdherent");

            }else if((methode != null) && (methode.equals("search"))){
                String s_cin = request.getParameter("s_cin");
                if(s_cin.isEmpty())
                    response.sendRedirect("CrudAdherent");
                else{
                    ArrayList<Adherent> adherents = new ArrayList<Adherent>();
                    adherents.add(ServiceAdherent.getAdherentByCin(s_cin));
                    request.setAttribute("adherents", adherents);
                    request.getRequestDispatcher("/WEB-INF/GestionAdherent/pagePrincipale.jsp").forward(request, response);
                }

            }else {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String cin = request.getParameter("cin");

                if(ServiceAdherent.ajouterAdherent(new Adherent(nom, prenom,cin))) {
                    response.sendRedirect("CrudAdherent");
                }else{
                    request.setAttribute("add_error","Problem occurs");
                    request.getRequestDispatcher("/WEB-INF/GestionAdherent/addAdherent.jsp").forward(request, response);
                }
            }
        }else if(path.equals("/CrudEmprunt")){
            String methode = request.getParameter("_method");
            if((methode != null) && (methode.equals("search"))){
                String s_cin = request.getParameter("s_cin");

                ArrayList<Emprunt> emprunts = ServiceEmprunt.getEmpruntsByCin(s_cin);
                if(emprunts.size() == 0)
                    request.setAttribute("noSuchEmp", "There is no such Emprunt!!!!!!!");
                request.setAttribute("emprunts",emprunts);
                request.getRequestDispatcher("/WEB-INF/GestionEmprunt/pagePrincipale.jsp").forward(request,response);
            }else if((methode != null) && (methode.equals("status_change"))){
                Integer id_Emp = Integer.parseInt(request.getParameter("st_idEmp"));
                String st_isbn = request.getParameter("st_isbn");
                ServiceEmprunt.retournerExemplaire(id_Emp,st_isbn);
                response.sendRedirect("CrudEmprunt");
            }else{
                String cin = request.getParameter("cin");
                String isbn = request.getParameter("isbn");
                if(ServiceEmprunt.ajouterEmprunt(cin,isbn)) {
                    response.sendRedirect("CrudEmprunt");
                }else{
                    request.setAttribute("add_error","Problem occurs");
                    request.getRequestDispatcher("/WEB-INF/GestionEmprunt/addEmprunt.jsp").forward(request, response);

                }
            }
        }
    }


}
