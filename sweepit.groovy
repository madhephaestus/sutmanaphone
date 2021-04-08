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


def slices = []

for(double i=0;i<360;i+=(360/10) ) {
	slices.add(myProfile.transformed(new Transform().roty(i)))
}
def parts=[]
try {
for(int i=0;i<slices.size();i++) {
	int next=i+1;
	if(next==slices.size())
		next=0
	println "Extruding "+i+" to "+next
	parts.add(Extrude.polygons(slices.get(i),slices.get(next)))
}


return parts
}catch(Throwable t) {
	BowlerStudio.printStackTrace(t)
	return slices
}