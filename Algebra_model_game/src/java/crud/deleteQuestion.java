package crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class deleteQuestion extends HttpServlet {
       
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String ruta = request.getRealPath("/");            
          
        String idtxt = request.getParameter("idList");  
        int id = Integer.valueOf(idtxt);
        
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Read Question</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");  

		try
		{
                    SAXBuilder builder = new SAXBuilder();
                    File xmlFile = new File(ruta + "preguntas.xml");

                    Document doc = (Document) builder.build(xmlFile);
                    Element rootNode = doc.getRootElement();
                    List questionList = rootNode.getChildren("PREGUNTA");                     
                    
//                    int idFinal = -1;
                    for(int i = 0; i < questionList.size(); i++){
                        Element questionHelp = (Element) questionList.get(i);
                        if(Integer.valueOf(questionHelp.getAttributeValue("ID")) == id){
                            out.println("<h3 style='color:skyblue' >El ID " + id + " found.</h3>"); 
                            questionList.remove(i); // Borrando la pregunta
//                            idFinal = i;
                            break;
                        }
                        else{
                            out.println("<h3>Reviewing question " + questionHelp.getAttributeValue("ID") + "</h3>");
                        }
                    }                    
                    
                    
                    
                    XMLOutputter xmlOutput = new XMLOutputter();

                    xmlOutput.setFormat(Format.getPrettyFormat()); // <-- Le agrega identación al lo agregado
                    FileWriter writer = new FileWriter(ruta + "preguntas.xml");                
                    xmlOutput.output(doc, writer);
                    writer.flush();
                    writer.close();                
                    
                    out.println("<p style='color: tomato'>The question was successfully deleted.</p>");                    
//                    response.sendRedirect("crudHome");                                                                                                                                                              
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
                        out.println("<p style='color: tomato'>ERROR: " + e.toString() + "</p>");
		}
		

	}


}