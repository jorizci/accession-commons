/*
 *
 * Copyright 2018 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package uk.ac.ebi.ampt2d.accession;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.ebi.ampt2d.accession.file.FileAccessioningDatabaseService;
import uk.ac.ebi.ampt2d.accession.file.FileMessage;
import uk.ac.ebi.ampt2d.accession.sample.SampleAccessioningDatabaseService;
import uk.ac.ebi.ampt2d.accession.sample.SampleMessage;
import uk.ac.ebi.ampt2d.accession.sha1.SHA1AccessionGenerator;
import uk.ac.ebi.ampt2d.accession.study.StudyAccessioningDatabaseService;
import uk.ac.ebi.ampt2d.accession.study.StudyMessage;
import uk.ac.ebi.ampt2d.accession.variant.VariantAccessioningDatabaseService;
import uk.ac.ebi.ampt2d.accession.variant.VariantMessage;

@Configuration
public class WebConfiguration {

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "file-accession")
    public AccessioningService<FileMessage, String> fileAccessionService() {
        return new AccessioningService<>(new BasicAccessionGenerator<>(), fileAccessioningDatabaseService());
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "file-accession")
    public FileAccessioningDatabaseService fileAccessioningDatabaseService() {
        return new FileAccessioningDatabaseService();
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "study-accession")
    public AccessioningService<StudyMessage, String> studyAccessionService() {
        return new AccessioningService<>(new SHA1AccessionGenerator<>(), studyAccessioningDatabaseService());
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "study-accession")
    public StudyAccessioningDatabaseService studyAccessioningDatabaseService() {
        return new StudyAccessioningDatabaseService();
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "sample-accession")
    public AccessioningService<SampleMessage, String> sampleAccessionService() {
        return new AccessioningService<>(new SHA1AccessionGenerator<>(), sampleAccessioningDatabaseService());
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "sample-accession")
    public SampleAccessioningDatabaseService sampleAccessioningDatabaseService() {
        return new SampleAccessioningDatabaseService();
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "variant-accession")
    public AccessioningService<VariantMessage, String> variantAccessionService() {
        return new AccessioningService(new SHA1AccessionGenerator<>(), variantAccessioningDatabaseService());
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "variant-accession")
    public VariantAccessioningDatabaseService variantAccessioningDatabaseService() {
        return new VariantAccessioningDatabaseService();
    }
}