/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polito.nexa.stdontologiesmapper;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.impl.ModelCom;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;

/**
 *
 * @author RMuzzi
 */
public class StatementsPair {

	final StatementPattern pattern;
	final StatementPattern replacement;

	public StatementsPair(StatementPattern pattern, StatementPattern replacement) {
		this.pattern = pattern;
		this.replacement = replacement;
	}

	public StatementPattern getPattern() {
		return pattern;
	}

	public StatementPattern getReplacement() {
		return replacement;
	}

	public static class StatementPattern extends StatementImpl {
		
		private boolean anySubject = false;
		private boolean anyPredicate = false;
		private boolean anyObject = false;

		public boolean isAnyObject() {
			return anyObject;
		}

		public boolean isAnyPredicate() {
			return anyPredicate;
		}

		public boolean isAnySubject() {
			return anySubject;
		}
		
		private void initializeWildcard(boolean anySubject, boolean anyPredicate, boolean anyObject ){
			this.anySubject = anySubject;
			this.anyPredicate = anyPredicate;
			this.anyObject = anyObject;
		}
		
		public StatementPattern(Resource subject, Property predicate, RDFNode object){
			this(subject, predicate, object, false, false, false);
		}
		public StatementPattern(Resource subject, Property predicate, RDFNode object, ModelCom model){
			this(subject, predicate, object, model, false, false, false);
		}
		public StatementPattern(Resource subject, Property predicate, RDFNode object, boolean anySubject, boolean anyPredicate, boolean anyObject ) {
			super(subject, predicate, object);
			initializeWildcard(anySubject, anyPredicate, anyObject);
		}
		public StatementPattern(Resource subject, Property predicate, RDFNode object, ModelCom model, boolean anySubject, boolean anyPredicate, boolean anyObject ) {
			super(subject, predicate, object, model);
			initializeWildcard(anySubject, anyPredicate, anyObject);
		}
		
	}

}