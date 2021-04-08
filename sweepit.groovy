import com.neuronrobotics.bowlerstudio.BowlerStudio
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.Polygon
import eu.mihosoft.vrl.v3d.Transform
import eu.mihosoft.vrl.v3d.svg.SVGLoad

File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/madhephaestus/sutmanaphone.git",//git repo URL
		"master",//branch
		"shape.svg.SVG"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())

HashMap<String,List<Polygon>> polygonsByLayer = s.toPolygons()
Polygon myProfile = polygonsByLayer.get("Slice 1").get(0)

return Extrude.revolve(myProfile,0,30)