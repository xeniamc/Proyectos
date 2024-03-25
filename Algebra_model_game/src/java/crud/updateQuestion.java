package crud;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

public class updateQuestion extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1000 * 1024;
    private int maxMemSize = 500 * 1024;
    private File file ;
       
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            
        filePath = request.getRealPath("/");             
        
        String idtxt = request.getParameter("idList");  
        int id = Integer.valueOf(idtxt);        

        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");        
        java.io.PrintWriter out = response.getWriter( );        
        
        if( !isMultipart ) {
           out.println("<html>");
           out.println("<head>");
           out.println("<title>ERROR UPLOAD</title>");  
           out.println("</head>");
           out.println("<body>");
           out.println("<p style='color: tomato'>An error occurred while trying to upload the file :c</p>"); 
           out.println("</body>");
           out.println("</html>");
           return;
        }        
        out.println("<h3 style='color:skyblue' >Iniciando subida de archivos...</h3>");                                                
        // PARA SUBIR ARCHIVOS      
        DiskFileItemFactory factory = new DiskFileItemFactory();           
        factory.setSizeThreshold(maxMemSize); //Tamaño maximo para guardar en memoria        
        factory.setRepository(new File(filePath)); // Ubicacion para archivos que superer el tamaño maximo       
        ServletFileUpload upload = new ServletFileUpload(factory); // Crea nuevo controlador de subida         
        upload.setSizeMax( maxFileSize ); // configuramos el tamaño maximo de archivos      
              
        out.println("<h3>Entrando el el TRY...</h3>");                                                
		try
		{   
                    // PARA EDITAR XML
                    SAXBuilder builder = new SAXBuilder(); // Se instancia un patron de diseño SAXBuilder
                    File xmlFile = new File(filePath + "preguntas.xml"); //Crea la ruta al xml                    
                    Document doc = (Document) builder.build(xmlFile); // Procesa el archivo
                    Element rootNode = doc.getRootElement(); // Obtiene ele elemento raiz del xml                                            
                                        
                    List questionList = rootNode.getChildren("PREGUNTA"); //Lista con todos los ejercicios  
                    int idFinal = -1;
                    for(int i = 0; i < questionList.size(); i++){
                        Element questionHelp = (Element) questionList.get(i);
                        if(Integer.valueOf(questionHelp.getAttributeValue("ID")) == id){
                            out.println("<h3 style='color:skyblue' >El ID " + id + " fue encontrado.</h3>"); 
                            idFinal = i;
                            break;
                        }
                        else{
                            out.println("<h3>Revisando pregunta " + questionHelp.getAttributeValue("ID") + "</h3>");
                        }
                    }
                    Element question = (Element) questionList.get(idFinal); //Selecciona el elemento pregunta a actualizar
                    
                    Element answer = question.getChild("RESPUESTA"); // El elemtento RESPUESTA                   
                    
                    String nombre = "*", tipo = "*", expr = "*", file_img = "*";
                    String dim1 = "*", dim2 = "*", bin1 = "*", bin2 = "*", bins = "*"; // USAR PARA BINOMIOS
                    String h2 = "*", w2 = "*"; // VAR's PERIMETRO
                    String sqr1 = "*", sqr3 = "*", sqr4 = "*"; // VAR's AREAS                
                    
                    // PARA OBTENER TODOS LOS DATOS ENVIADOS (UPLOADS & TEXT INPUTS)
                    List fileItems = upload.parseRequest(request); // recibe la peticion de subida                    
                    Iterator i = fileItems.iterator(); // Procesa la subida de los intems

                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Upload Files</title>");  
                    out.println("</head>");
                    out.println("<body>"); 
                    
                    while ( i.hasNext() ) {
                       FileItem fi = (FileItem)i.next();

                       if ( fi.isFormField () ) {

                            switch(fi.getFieldName()){ // SWITCH PARA OBTENER CADA FIELD
                                case "ejer":
                                    nombre = fi.getString();
                                    question.setAttribute("NOMBRE", nombre);
                                    out.println("<h3>" + nombre + "</h3>");                                          
                                    break;
                                case "tipo":
                                    tipo = fi.getString();
                                    question.setAttribute("TIPO", tipo);
                                    question.setAttribute("WIDTH", "2");
                                    question.setAttribute("HEIGHT", "2");
                                    out.println("<h3>" + tipo + "</h3>");                                          
                                    break;
                                //PERIMETRO
                                case "h2":
                                    h2 = fi.getString();
                                    question.setAttribute("SIZEH1", "x");
                                    question.setAttribute("SIZEH2_ANSWER", h2);                                    
                                    out.println("<h3>" + h2 + "</h3>");                                          
                                    break;
                                case "w2":
                                    w2 = fi.getString();
                                    question.setAttribute("SIZEW1", "x");
                                    question.setAttribute("SIZEW2_ANSWER", w2);                                    
                                    out.println("<h3>" + w2 + "</h3>");                                          
                                    break;
                                //AREAS
                                case "area1":
                                    sqr1 = fi.getString();
                                    question.setAttribute("SQUARE1", sqr1);
                                    question.setAttribute("SQUARE2", "x^2");                                    
                                    out.println("<h3>" + sqr1 + "</h3>");                                          
                                    break;
                                case "area3":
                                    sqr3 = fi.getString();
                                    question.setAttribute("SQUARE3", sqr3);                                    
                                    out.println("<h3>" + sqr3 + "</h3>");                                          
                                    break;
                                case "area4":
                                    sqr4 = fi.getString();
                                    question.setAttribute("SQUARE4", sqr4);                                    
                                    out.println("<h3>" + sqr4 + "</h3>");                                          
                                    break;
                                //AREA TOTAL
                                case "expresion":
                                    expr = fi.getString();
                                    answer.setAttribute("EXPRETION", expr);                                    
                                    out.println("<h3>" + expr + "</h3>");                                          
                                    break;
                                //DIMENSIONES
                                case "dim1":
                                    dim1 = fi.getString();
                                    int dim1_int = Integer.parseInt(dim1);
                                    if(dim1_int > 0){
                                        bin1 = "(x+"+dim1_int+")";
                                    } else{
                                        bin1 = "(x"+dim1_int+")";
                                    }
                                    out.println("<h3>" + bin1 + "</h3>");                                          
                                    break;
                                case "dim2":
                                    dim2 = fi.getString();
                                    int dim2_int = Integer.parseInt(dim2);
                                    if(dim2_int > 0){
                                        bin2 = "(x+"+dim2_int+")";
                                    } else{
                                        bin2 = "(x"+dim2_int+")";
                                    }
                                    out.println("<h3>" + bin2 + "</h3>");                                          
                                    break;

                                default:
                                   out.println("<h3 style='color:tomato' >A field was found that did not expect to be received, its content: " + fi.getString() + "</h3>");
                                break;
                            }
                        }
                    }
                    
                    answer.setText(bin1+bin2);
                    out.println("<h3>HE TERMINADO DE REVISAR LOS INPUTS</h3>");                                          
                    
                    i = fileItems.iterator();                    
                    while ( i.hasNext() ) {
                       FileItem fi = (FileItem)i.next();
//                       ESTE IF ES PARA ENCONTRAR LOS UPLOADS
                       if ( !fi.isFormField () ) {
                          // Obtiene todos los parametros de subida
                          String fieldName = fi.getFieldName();
                          String fileName = fi.getName();
                          String contentType = fi.getContentType();
                          boolean isInMemory = fi.isInMemory();
                          long sizeInBytes = fi.getSize();
                          String size = String.valueOf(sizeInBytes);
                          
                          if(sizeInBytes > 0){
                          // Guarda el archivo
                            if( fileName.lastIndexOf("\\") >= 0 ) {
                               file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                            } else {
                               file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                            }
                              try {
                                  fi.write( file ) ;
                              } catch (Exception ex) {
                                  Logger.getLogger(updateQuestion.class.getName()).log(Level.SEVERE, null, ex);
                              }
                            out.println("<p>Archivo subido: " + fileName + "</p>");

                              switch(fieldName){ // SWITCH PARA GUARDAR EL NOMBRE DE LA IMAGEN
                                  case "file_image":
                                    file_img = fileName;
                                    question.setAttribute("IMAGE", file_img);
                                    break;                                   
                                  default:
                                      out.println("<h3 style='color:tomato' >An unexpected file was tried to upload, its name is: '" + fileName + "' field: '" + fieldName+ "'</h3>");                                        
                                      break;
                                }
                            }  
                        }
                    }

                    XMLOutputter xmlOutput = new XMLOutputter();

                    xmlOutput.setFormat(Format.getPrettyFormat()); // <-- Le agrega identación al lo agregado
                    FileWriter writer = new FileWriter(filePath + "preguntas.xml");                
                    xmlOutput.output(doc, writer);
                    writer.flush();
                    writer.close();                            
                                                                                         
                    response.sendRedirect("/Algebra_model_game/");                                    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}
        public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, java.io.IOException 
        {

           throw new ServletException("GET method used with " +
              getClass( ).getName( )+": POST method required.");
        }
}