/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polito.nexa.stdontologiesmapper;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import it.polito.nexa.stdontologiesmapper.StatementsPair.StatementPattern;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author RMuzzi
 */
public class DefaultOntologyMapper implements OntologyMapper {

	@Override
	public Model mapModel(Model model, List<StatementsPair> mapping) {
		Model result = ModelFactory.createDefaultModel();
		List<Statement> stmts = stmtIteratorToList(model.listStatements());

		stmts.forEach(st -> {
			Statement res = mappingIteration(mapping, st) ;
			System.out.println("Adding -> "+res);
			result.add( res );
		});

		return result;
	}
	
	public static Statement mappingIteration(List<StatementsPair> mapping, Statement st){
		for (StatementsPair sp : mapping) {
			StatementPattern pattern = sp.getPattern();
			StatementPattern replacement = sp.getReplacement();
			if (equals(pattern, st))
				return replace(replacement, st);
		}
		return st;
	}
	
	public static Statement replace(Statement pr, Statement original ){
		Resource subject	= patternReplace(Statement::getSubject, pr, original);
		Property predicate	= patternReplace(Statement::getPredicate, pr, original);
		RDFNode object		= patternReplace(Statement::getObject, pr, original);
		return new StatementImpl(subject, predicate, object);
	}
	
	public static <R,T> T patternReplace(Function<R,T> methodReference, R replacement, R original){
		return (methodReference.apply(replacement)==null)?
					methodReference.apply(original):
					methodReference.apply(replacement);
	}
	
	public static boolean equals(StatementPattern pattern, Statement st){
		return (
				( pattern.isAnySubject() || pattern.getSubject().equals(st.getSubject()) )
			&&	( pattern.isAnyPredicate()|| pattern.getPredicate().equals(st.getPredicate()) )
			&&	( pattern.isAnyObject() || pattern.getObject().equals(st.getObject()) )
			);
		
		
	}

	public static List<Statement> stmtIteratorToList(StmtIterator it) {
		List<Statement> statements = new ArrayList<>();
		it.forEachRemaining(st -> {
			statements.add(st);
		});
		return statements;
	}
}
