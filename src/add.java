import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//買い物リストに商品を追加

@WebServlet("/add")

public class add extends HttpServlet {
	
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(
					"java:/comp/env/jdbc/webapp");
			Connection con = ds.getConnection();
			int comm = Integer.parseInt( request.getParameter("comm"));
			
			PreparedStatement st = con.prepareStatement(
					"update comm_table set add_id=2 where comm_id=?");
			st.setInt(1, comm);
			
			//int count = Integer.parseInt( request.getParameter("count"));
			//System.out.println(count);
			st.executeUpdate();
			st.close();
			con.close();
			response.sendRedirect("http://localhost:8080/R03Team06/list");

			} catch (Exception e) {
				out.println("<pre>");
				e.printStackTrace(out);
			}
		
	}
	
}