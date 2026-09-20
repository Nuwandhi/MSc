/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.ViewModel;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.*;
import org.apache.jena.ontology.OntClass;
//import jena.examples.ontology.classHierarchy.Main;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
//import javax.management.Query;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.util.FileManager;
import org.apache.log4j.varia.NullAppender;

/**
 *
 * @author User
 */
public class ViewController {
//    public static ArrayList<ViewModel> viewDiseases(String symptoms){
//            org.apache.log4j.BasicConfigurator.configure(new NullAppender());
//               //FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
//               OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
//               model.read("I:\\JavaNotes\\DSS\\src\\Controll\\DSS.owl", "RDF/XML");
//            ArrayList list=new ArrayList();
//             String[] s=new String[]{symptoms};
//               String queryString=null;
//              for(int i=0;i<=s.length-1;i++){
//                queryString =
//                         "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" +
//                         "PREFIX dss:<http://www.semanticweb.org/user/ontologies/2018/5/DSS.owl#>"+
//                         "PREFIX owl:<http://www.w3.org/2002/07/owl#>"+
//                         "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
//                             "select distinct ?s where{"+
//                              "?s rdfs:subClassOf ?restriction. "
//                              + "?restriction owl:onProperty dss:hasSymptoms."
//                              +"?restriction  owl:someValuesFrom dss:"+s[i].split("\n")+"."
//         //                     +"?restriction  owl:someValuesFrom"."
//         //                      +"?restriction  owl:someValuesFrom 
//                              + "}";
//
//
//              }
//              Query query = QueryFactory.create(queryString);
//               QueryExecution qexec=QueryExecutionFactory.create(query,model);
//               org.apache.jena.query.ResultSet results=qexec.execSelect();
//               //ResultSetFormatter.out(System.out,results,query);
//               while (results.hasNext())
//               {
//                  QuerySolution qs=results.next();
//                  list.add(qs.getLiteral(queryString));
//               }
//       return list;
//    }
   public static ArrayList<ViewModel> Search(String cat){
   
       org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        // create the base model
         OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM );
         base.read( "I:\\JavaNotes\\DSS\\src\\Controll\\DSS.owl", "RDF/XML" );
         String ns="http://www.semanticweb.org/user/ontologies/2018/5/DSS.owl#";
         ArrayList list=new ArrayList();
          OntClass k=base.getOntClass(ns+cat);
                 for (Iterator<OntClass> i = k.listSubClasses(); i.hasNext(); ) {
                         OntClass sup=(OntClass)i.next();
                         //System.out.println(sup.getLocalName());
                         list.add(sup.getLocalName());
                 }
              //base.write(System.out,"RDF/XML");
                 return list;
   }
}
