/*
 * Copyright 2013 MovingBlocks
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
 */
package org.terasology.rendering.assets.mesh;

import gnu.trove.list.TFloatList;
import gnu.trove.list.TIntList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.asset.AssetLoader;
import org.terasology.engine.module.Module;
import org.terasology.rendering.collada.ColladaLoader;

import com.google.common.base.Charsets;

/**
 * Importer for Collada data exchange model files.  Supports mesh data
 * 
 * The development of this loader was greatly influenced by 
 * http://www.wazim.com/Collada_Tutorial_1.htm
 *
 * @author mkienenb@gmail.com
 */

public class ColladaMeshLoader extends ColladaLoader implements AssetLoader<MeshData> {

    private static final Logger logger = LoggerFactory.getLogger(ColladaMeshLoader.class);

    @Override
    public MeshData load(Module module, InputStream stream, List<URL> urls) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charsets.UTF_8));

        String contents = loadDataAsString(reader);
        try {
            parseData(contents);
        } catch (ColladaParseException e) {
            logger.error("Unable to load mesh", e);
            return null;
        }

        MeshData data = new MeshData();
        TFloatList verticesMesh = data.getVertices();
        TFloatList texCoord0Mesh = data.getTexCoord0();
        TFloatList normalsMesh = data.getNormals();
        TIntList indicesMesh = data.getIndices();

        verticesMesh.addAll(this.vertices);
        texCoord0Mesh.addAll(this.texCoord0);
        normalsMesh.addAll(this.normals);
        indicesMesh.addAll(this.indices);

        if (data.getVertices() == null) {
            throw new IOException("No vertices define");
        }
        //if (data.getNormals() == null || data.getNormals().size() != data.getVertices().size()) {
        //    throw new IOException("The number of normals does not match the number of vertices.");
        //}
        if (data.getTexCoord0() == null || data.getTexCoord0().size() / 2 != data.getVertices().size() / 3) {
            throw new IOException("The number of tex coords does not match the number of vertices.");
        }

        return data;
    }

}
