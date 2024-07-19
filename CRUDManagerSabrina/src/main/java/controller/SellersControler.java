package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;

@WebServlet(urlPatterns = {"/serllers", "/sellers/form","/seller/Insert"})

public class SellersControler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		switch (action) {
		case "/crud-manager/seller/form": {
			loadComponies(req);
			ControllerUtil.forward(req, resp, "/form-seller.jsp");	
			break;
		}
		default:
			listSelles(req);
			ControllerUtil.transferSessionMessagesToRequest(req);
			ControllerUtil.forward(req, resp, "/sellers.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		 switch (action) {
		case "/crud-manager/seller/insert": {
			
			insertSeller(req);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private void insertSeller(HttpServletRequest req) {
		String sellerName = req.getParameter("seller_name");
		String sellerEmail = req.getParameter("seller_email");
		String sellerFone = req.getParameter("seller_fone");
		String sellerCompany = req.getParameter("seller_empresa");
		int sellerCompanyId = Integer.parseInt(sellerCompany);
		
		Seller seller = new Seller();
		seller.SetName(sellerName);
		seller.SetEmail(sellerEmail);
		seller.SetFone(sellerFone);
		seller.setCompany(new Company(sellerCompanyId));
		
		SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
		
		try {
			if(dao.save(seller))
				ControllerUtil.sucessMessage(req, "Vendedor" + seller.getContent() + "' atualizado com sucesso.");
			else
				ControllerUtil.errorMessage(req, "Vendedor '" + seller.getContent() + "' não pode ser atualizado.");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(sellerName + "" + sellerEmail + "" + sellerFone + "" + sellerCompany + "");
	}

	private void loadComponies(HttpServletRequest req) {
		List<Company> companies = new ArrayList<>();
		 CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		 try {
				companies = dao.listAll();
			} catch(ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao carregar empresas");
			}
		 req.setAttribute("empresas", companies);
	}

	private void listSelles(HttpServletRequest req) {
		SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
		
		List<Seller> sellers = new ArrayList()<>;
		
		try {
			sellers = dao.listAll();
		} catch(ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao carregar dados dos vendedores");
		}
		
		req.setAttribute("vendedores", sellers);
	}
}
