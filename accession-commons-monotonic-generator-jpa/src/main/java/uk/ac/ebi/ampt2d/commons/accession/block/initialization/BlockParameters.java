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
package uk.ac.ebi.ampt2d.commons.accession.block.initialization;

import java.util.Map;

/**
 * Initialization parameters for blocks of monotonic accession
 */
public class BlockParameters {

    private long blockStartValue;
    private long blockSize;
    private long nextBlockInterval;

    public BlockParameters(Map<String, String> propertiesMap) {
        this.blockStartValue = Long.parseLong(propertiesMap.get(BlockInitializationParameterNames.BLOCK_START_VALUE));
        this.blockSize = Long.parseLong(propertiesMap.get(BlockInitializationParameterNames.BLOCK_SIZE));
        this.nextBlockInterval = Long.parseLong(propertiesMap.get(BlockInitializationParameterNames.NEXT_BLOCK_INTERVAL));
    }

    public static void checkIsBlockSizeValid(Map<String, String> blockInitializations) {
        try {
            if (blockInitializations == null
                    || Long.parseLong(blockInitializations.get(BlockInitializationParameterNames.BLOCK_SIZE)) <= 0
                    || Long.parseLong(blockInitializations.get(BlockInitializationParameterNames.NEXT_BLOCK_INTERVAL)) < 0
                    || Long.parseLong(blockInitializations.get(BlockInitializationParameterNames.BLOCK_START_VALUE)) < 0)
                throw new BlockInitializationException("BlockParameters not initialized for the category or invalid");
        } catch (RuntimeException e) {
            throw new BlockInitializationException("BlockParameters not initialized for the category or invalid");
        }
    }

    public long getBlockStartValue() {
        return blockStartValue;
    }

    public long getBlockSize() {
        return blockSize;
    }

    public long getNextBlockInterval() {
        return nextBlockInterval;
    }

    @Override
    public String toString() {
        return "BlockParameters{" +
                "blockStartValue=" + blockStartValue +
                ", blockSize=" + blockSize +
                ", nextBlockInterval=" + nextBlockInterval +
                '}';
    }

    private interface BlockInitializationParameterNames {

        String BLOCK_SIZE = "blockSize";
        String BLOCK_START_VALUE = "blockStartValue";
        String NEXT_BLOCK_INTERVAL = "nextBlockInterval";
    }
}
