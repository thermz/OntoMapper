/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polito.nexa.stdontologiesmapper;

import com.hp.hpl.jena.rdf.model.Model;
import java.util.List;

/**
 *
 * @author RMuzzi
 */
public interface OntologyMapper {
	
	public Model mapModel(Model model, List<StatementsPair> mapping);
	
}
