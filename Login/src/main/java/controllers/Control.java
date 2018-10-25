package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.google.gson.Gson;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Control() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");

		HttpSession oSession = request.getSession();
		String strOp = request.getParameter("op");
		String strJson = "";
		String user = "hec3555";
		String pass = "8c5fd24986a4248cebf8be6f4aa27cab81412c1ca042830765eac60544f880c5";
		//Gson gson = new Gson();
		if (strOp != null) {
			if (strOp.equalsIgnoreCase("")) {
				response.setStatus(500);
				strJson = strJson(500, "No ha solicitado ninguna operacion.");
			} else {
				if (strOp.equalsIgnoreCase("login")) {
					String strUser = request.getParameter("user");
					String strPass = request.getParameter("pass");
					if (strUser != null && strPass != null) {
						if (strUser.equalsIgnoreCase(user) && strPass.equalsIgnoreCase(pass)) {
							oSession.setAttribute("sesionvar", strUser);
							response.setStatus(200);
							strJson = strJson(200, "You are logged in.");
						} else {
							response.setStatus(401);
							strJson = strJson(401, "Authentication error");
						}
					}else {
						strJson = strJson(401, "User or pass not specified.");
					}
				}
				if (strOp.equalsIgnoreCase("logout")) {
					oSession.invalidate();
					response.setStatus(200);
					strJson = strJson(200, "Session is closed.");
				}
				if (strOp.equalsIgnoreCase("check")) {
					String strUserName = (String) oSession.getAttribute("sesionvar");
					if (strUserName == null) {
						response.setStatus(500);
						strJson = strJson(500, "You are NOT logged in.");
					} else {
						response.setStatus(200);
						strJson = strJson(200, "You are logged in.");
					}
				}
				if (strOp.equalsIgnoreCase("secret")) {
					String strUserName = (String) oSession.getAttribute("sesionvar");
					if (strUserName == null) {
						response.setStatus(401);
						strJson = strJson(401, "You are NOT allowed to show this message.");
					} else {
						response.setStatus(200);
						strJson = strJson(200, "El mensaje es.... NADA TIE SENTIDO FOLAGOR");
					}
				}
			}
		} else {
			response.setStatus(500);
			strJson = strJson(500, "No ha solicitado ninguna operacion.");
		}

		response.getWriter().append(strJson);

	}

	public String strJson(int status, String msg) {
		String strJsonM = "{\"status\":" + status + ",\"msg\":\"" + msg + "\"}";
		return strJsonM;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
