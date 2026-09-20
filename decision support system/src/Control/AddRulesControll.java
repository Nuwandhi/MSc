/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


/**
 *
 * @author User
 */

import Model.AddRulesModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//import javax.annotation.Resource;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Restriction;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateRequest;
import org.apache.log4j.varia.NullAppender;
import org.semanticweb.owlapi.change.OntologyAnnotationChangeData;

public class AddRulesControll {
    public static int addClass(AddRulesModel m) throws FileNotFoundException{
       // org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        // create the base model
        
         OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM );
         base.read( "I:\\JavaNotes\\DSS\\src\\Controll\\DSS.owl", "RDF/XML" );
         
         String ns="http://www.semanticweb.org/user/ontologies/2018/6/DSS#";
         String dcat=m.getdCategery().replaceAll("\\s", "");
         String dname=m.getdName().replaceAll("\\s", "");
         String[] signs=m.getSigns().split("\n");
         String patientText=m.getPatient();
         String ruleno=m.getRuleno();
         String rule=m.getRule();
         
        
        //Create Classes
         
         OntClass c0= base.createClass(ns+"Diseases");
         OntClass c1= base.createClass(ns+dcat);
         OntClass c2= base.createClass(ns+"Patient");
         OntClass c3= base.createClass(ns+"Symptoms");
         //Create Individuals
         Individual disease=base.createIndividual(ns+dname,c1);
         Individual patient=base.createIndividual(ns+patientText,c2);
         
        //Create Sub Classes
        c0.addSubClass(c1);
        
           for(int i=0;i<=signs.length-1;i++){
                    
                    Individual c= base.createIndividual(ns+signs[i].replaceAll("\\s", ""),c3);
                    ObjectProperty has=base.getObjectProperty(ns+"hasSymptom");
                    base.add(patient,has,c);

            }
                
           //Create Rule
          try {
              FileWriter fw=new FileWriter("I:\\JavaNotes\\DSS\\src\\Controll\\rule.txt", true);
              fw.write("\n"+"["+ruleno+":"+rule+"]"+"\n");
              fw.close(); 
              
        } catch (Exception e) {
            e.printStackTrace();
        }   
              
        //Create reasoner
          
        Reasoner reasoner=new GenericRuleReasoner(Rule.rulesFromURL("I:\\JavaNotes\\DSS\\src\\Controll\\rule.txt"));
        InfModel infModel=ModelFactory.createInfModel(reasoner, base);
        StmtIterator it=infModel.listStatements();
        
        while(it.hasNext())
        {
            Statement stmt=it.nextStatement();
            org.apache.jena.rdf.model.Resource s=stmt.getSubject();
            Property pre=stmt.getPredicate();
            RDFNode o=stmt.getObject();
            base.add(s, pre, o);
            
        }
              
        PrintStream path=new PrintStream("I:\\JavaNotes\\DSS\\src\\Controll\\DSS.owl");
        base.write(path,"RDF/XML");

        return 1;

   }
    
}
