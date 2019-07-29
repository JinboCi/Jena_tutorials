package com.company;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

public class Main extends Object {

    public static void main (String args[]) throws IOException {
        Model m = ModelFactory.createDefaultModel();
        String nsA = "http://somewhere/else#";
        String nsB = "http://nowhere/else#";
        Resource root = m.createResource( nsA + "root" );
        Property P = m.createProperty( nsA + "P" );
        Property Q = m.createProperty( nsB + "Q" );
        Resource x = m.createResource( nsA + "x" );
        Resource y = m.createResource( nsA + "y" );
        Resource z = m.createResource( nsA + "z" );
        m.add( root, P, x ).add( root, P, y ).add( y, Q, z );
        System.out.println( "# -- no special prefixes defined" );
        String fileName1 = "abbreviation1.rdf";
        FileWriter out1 = new FileWriter( fileName1 );
        m.write( out1, "RDF/XML-ABBREV" );
        System.out.println( "# -- nsA defined" );
        m.setNsPrefix( "nsA", nsA );
        String fileName2 = "abbreviation2.rdf";
        FileWriter out2 = new FileWriter( fileName2 );
        m.write( out2, "RDF/XML-ABBREV" );
        System.out.println( "# -- nsA and cat defined" );
        m.setNsPrefix( "cat", nsB );
        String fileName3 = "abbreviation3.rdf";
        FileWriter out3 = new FileWriter( fileName3 );
        m.write( out3, "RDF/XML-ABBREV" );
        try {
            out1.close();
            out2.close();
            out3.close();
        }
            catch (IOException closeException) {
                // ignore
            }
        }
    }
