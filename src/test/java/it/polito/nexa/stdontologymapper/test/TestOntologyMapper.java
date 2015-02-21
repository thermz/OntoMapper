/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polito.nexa.stdontologymapper.test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import it.polito.nexa.stdontologiesmapper.DefaultOntologyMapper;
import it.polito.nexa.stdontologiesmapper.OntologyMapper;
import it.polito.nexa.stdontologiesmapper.StatementsPair;
import it.polito.nexa.stdontologiesmapper.StatementsPair.StatementPattern;
import java.io.InputStream;
import static java.util.Arrays.asList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author RMuzzi
 */
public class TestOntologyMapper {

	@Test
	public void testOM(){
		OntologyMapper om = new DefaultOntologyMapper();
		
		Model m = om.mapModel(
					fakeModel(),
					fakeMapping());
		
		System.out.println(m.toString());
		
	}
	
	private Model fakeModel(){
		Model result = ModelFactory.createDefaultModel();
		result.read(getMockFile(), null, "N-TRIPLE");
		return result;
	}
	private List<StatementsPair> fakeMapping(){
		Resource subjectPattern = new ResourceImpl();
		Property predPattern = new PropertyImpl("http://www.recshop.fake/cd#year");
		RDFNode objectPattern = new ResourceImpl();
		StatementPattern pattern = new StatementPattern(subjectPattern, predPattern, objectPattern,
										true,false,true);
		
		Resource subject = new ResourceImpl();
		Property pred = new PropertyImpl("http://www.recshop.fake/new#year");
		RDFNode object = new ResourceImpl();
		StatementPattern replacement = new StatementPattern(subject, pred, object,
										true,false,true);
		
		return asList( new StatementsPair(pattern, replacement) );
	}

	private InputStream getMockFile() {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("modelInputMock.ntriple");
	}
}
