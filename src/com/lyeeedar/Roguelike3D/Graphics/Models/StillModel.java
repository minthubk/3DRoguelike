/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.lyeeedar.Roguelike3D.Graphics.Models;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.lyeeedar.Roguelike3D.Graphics.Materials.Material;

public class StillModel implements Model {
	final public StillSubMesh[] subMeshes;

	public StillModel (SubMesh[] subMeshes) {
		this.subMeshes = new StillSubMesh[subMeshes.length];
		for (int i = 0; i < subMeshes.length ; ++i) {
			this.subMeshes[i] = (StillSubMesh)subMeshes[i];
		}	
	}

	@Override
	public void render (ShaderProgram program, StillModelAttributes attributes) {
		
		attributes.material.bind(program);
		
		int len = subMeshes.length;
		for (int i = 0; i < len; i++) {
			StillSubMesh subMesh = subMeshes[i];
			subMesh.mesh.render(program, subMesh.primitiveType);
		}
	}

	@Override
	public Model getSubModel (String... subMeshNames) {
		ArrayList<SubMesh> subMeshes = new ArrayList<SubMesh>();
		for (String name : subMeshNames)
			for (StillSubMesh subMesh : this.subMeshes)
				if (name.equals(subMesh.name)) subMeshes.add(subMesh);
		if (subMeshes.size() > 0) return new StillModel(subMeshes.toArray(new StillSubMesh[subMeshes.size()]));
		return null;
	}

	@Override
	public StillSubMesh getSubMesh (String name) {
		for (StillSubMesh subMesh : subMeshes) {
			if (subMesh.name.equals(name)) return subMesh;
		}
		return null;
	}

	@Override
	public SubMesh[] getSubMeshes () {
		return subMeshes;
	}

	private final static BoundingBox tmpBox = new BoundingBox();

	@Override
	public void getBoundingBox (BoundingBox bbox) {
		bbox.inf();
		for (int i = 0; i < subMeshes.length; i++) {
			subMeshes[i].mesh.calculateBoundingBox(tmpBox);
			bbox.ext(tmpBox);
		}
	}

	@Override
	public void dispose () {
		for (int i = 0; i < subMeshes.length; i++) {
			subMeshes[i].mesh.dispose();
		}
	}

	@Override
	public void setMaterial(Material material) {
		// TODO Auto-generated method stub
		
	}
}
