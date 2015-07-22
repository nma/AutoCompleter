package com.fun;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.List;

public class Main {

    private static class Arguments {
        @Option(name = "-searchFile",usage="the file to auto complete from.")
        public String fileName;

        @Option(name = "-searchStrings",usage="the comma seperated string to auto complete.")
        public String autoCompleteStrings;
    }

    public static void main(String[] args) throws CmdLineException {
        Arguments arguments = new Arguments();
        CmdLineParser cmd = new CmdLineParser(arguments);
        cmd.parseArgument(args);

        File inputFile = new File(arguments.fileName);
        AutoCompleter autoCompleter = new AutoCompleter(inputFile);
        autoCompleter.preprocess();

        for (String stringToAutoComplete : arguments.autoCompleteStrings.split(",")) {
            System.out.println("AutoComplete: " + stringToAutoComplete);
            List<String> potentialCandidates = autoCompleter.lookup(stringToAutoComplete);

            System.out.println("Potential Candidates:");
            for (String potentialCandidate : potentialCandidates) {
                System.out.println(potentialCandidate);
            }
            System.out.println("===End Batch===");
        }
    }
}
