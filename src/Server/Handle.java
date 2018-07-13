package Server;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Impl.DBupdate;
import Impl.Parama;
import algorithm.Parama2Json;
import algorithm.ParameterProcess;

/**
 * Servlet implementation class Handle
 */
@Path("/")
public class Handle{
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Handle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		ParameterProcess pp=new ParameterProcess();

		String text = request.getParameter("description");
		String solutionid = request.getParameter("solution");
		Set<Parama> parametes;
		try {
			HttpSession session = request.getSession();
			parametes = pp.getParemetes(text);//原始捕捉的参数
			Process.inputsolution(solutionid,text,parametes);
//			session.setAttribute("text",text);
//			session.setAttribute("parameters", parametes);
			response.setContentType("text/html;charset=UTF-8");
			Writer out = response.getWriter();
			out.write(Parama2Json.GsonListStr(parametes));
			//response.sendRedirect("index.jsp");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(text);

	}

	@Path("getparametes")
	@POST
    @Produces(MediaType. APPLICATION_JSON)
	public void getParametes(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		ParameterProcess pp=new ParameterProcess();

		String text = request.getParameter("description");
		String solutionid = request.getParameter("solution");
		Set<Parama> parametes;
		try {
			HttpSession session = request.getSession();
			parametes = pp.getParemetes(text);//原始捕捉的参数
			Process.inputsolution(solutionid,text,parametes);
//			session.setAttribute("text",text);
//			session.setAttribute("parameters", parametes);
			response.setContentType("text/html;charset=UTF-8");
			Writer out = response.getWriter();
			out.write(Parama2Json.GsonListStr(parametes));
			//response.sendRedirect("index.jsp");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}




	@Path("question")
	@POST
    @Produces(MediaType. APPLICATION_JSON)
	public void getQuestion() {
		System.out.println("yoyoyo");
	}
}
