package crud;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.Document;
import org.jdom.Element;

public class createQuestion extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 10240 * 1024;
    private int maxMemSize = 5120 * 1024;
    private File file ;
       
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            
        filePath = request.getRealPath("/");             

        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");        
        java.io.PrintWriter out = response.getWriter( );        
        
        if( !isMultipart ) {
           out.println("<html>");
           out.println("<head>");
           out.println("<title>ERROR UPLOAD</title>");  
           out.println("</head>");
           out.println("<body>");
           out.println("<p style='color: tomato'>Ocurrio un error en la subida de archivos :c</p>"); 
           out.println("</body>");
           out.println("</html>");
           return;
        }        
        
        // PARA SUBIR ARCHIVOS      
        DiskFileItemFactory factory = new DiskFileItemFactory();           
        factory.setSizeThreshold(maxMemSize); //Tamaño maximo para guardar en memoria        
        factory.setRepository(new File(filePath)); // Ubicacion para archivos que superer el tamaño maximo       
        ServletFileUpload upload = new ServletFileUpload(factory); // Crea nuevo controlador de subida         
        upload.setSizeMax( maxFileSize ); // configuramos el tamaño maximo de archivos      
              

		try
		{   
                    // PARA EDITAR XML
                    SAXBuilder builder = new SAXBuilder(); // Se instancia un patron de diseño SAXBuilder
                    File xmlFile = new File(filePath + "preguntas.xml"); //Crea la ruta al xml                    
                    Document doc = (Document) builder.build(xmlFile); // Procesa el archivo
                    Element rootNode = doc.getRootElement(); // Obtiene ele elemento raiz del xml                    
//                    out.println("<h3>INICIANDO...</h3>");
                    List questionList = rootNode.getChildren("PREGUNTA");
//                    out.println("<h3>Lista preguntas obtenida...</h3>");
//                    out.println("<h3>" + questionList.size() + "</h3>");
                    Element lastQuestion = (Element) questionList.get(questionList.size() - 1);
//                    out.println("<h3>Ultimo elemento obtenido...</h3>");
                    String lastID = lastQuestion.getAttributeValue("ID");                    
//                    out.println("<h3>" + lastID + "</h3>");
                    int newID = Integer.valueOf(lastID);
                    newID++;
                    out.println("<h3>Creando la nueva pregunta ID: " + newID + "</h3>");

                    Element question = new Element("PREGUNTA"); 
                    question.setAttribute("ID", String.valueOf(newID));                    
                    Element answer = new Element("RESPUESTA");
                    
                    String nombre = "*", tipo = "*", expr = "*", file_img = "*";
                    String dim1 = "*", dim2 = "*", bin1 = "*", bin2 = "*", bins = "*"; // USAR PARA BINOMIOS
                    String h2 = "*", w2 = "*"; // VAR's PERIMETRO
                    String sqr1 = "*", sqr3 = "*", sqr4 = "*"; // VAR's AREAS
                                  
                    
                    // PARA OBTENER TODOS LOS DATOS ENVIADOS (UPLOADS & TEXT INPUTS)
                    List fileItems = upload.parseRequest(request); // recibe la peticion de subida                    
                    Iterator i = fileItems.iterator(); // Procesa la subida de los items

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

                          // Guarda el archivo
                          if( fileName.lastIndexOf("\\") >= 0 ) {
                             file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                          } else {
                             file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                          }
                          fi.write( file ) ;
                          out.println("<p>Archivo subido: " + fileName + "</p>");

                            switch(fieldName){ // SWITCH PARA GUARDAR EL NOMBRE DE LA IMAGEN
                                //IMAGEN
                                case "file_image":
                                    file_img = fileName;
                                    break;                                    
                                default:
                                    out.println("<h3 style='color:tomato' >An unexpected file was tried to upload, its name is: '" + fileName + "' field: '" + fieldName+ "'</h3>");                                        
                                    break;
                            }
                        // out.println("<p>file: " + file.toString() + "</p>"); <-- toda la ruta en la PC
                       }
                    }     
                    // GUARDAR ATRIBUTO IMAGE   
                    question.setAttribute("IMAGE", file_img);   
                    // AÑADIR ELEMENTO RESPUESTA A PREGUNTA (ELEMENTO PADRE)
                    question.addContent(answer);
                    // AÑADIR PREGUNTA AL NODO RAIZ             
                    out.println("<h3>Antes de añadir</h3>"); 
                    rootNode.addContent(question);                    
                    out.println("<h3>Despues de añadir</h3>"); 
                    XMLOutputter xmlOutput = new XMLOutputter();

                    xmlOutput.setFormat(Format.getPrettyFormat()); // <-- Le agrega identación al lo agregado
                    FileWriter writer = new FileWriter(filePath + "preguntas.xml");                
                    xmlOutput.output(doc, writer);
                    writer.flush();
                    writer.close();                            
                    out.println("<h3>GUARDADA</h3>"); 
                    response.sendRedirect("");                                                                                                                       
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