/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.resources;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Syynth
 */
public class ResourceManager 
{
    private Map rMap;
    public ResourceManager()
    {
        init();
    }
    
    private void init()
    {
        
        //load Textures
        File[] textureFiles = listFilesAsArray(new File("assets"),new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("png");
            }
        },true);
        for(int i = 0; i<textureFiles.length; i++)
        {
            //if the other assets from the resource have already been loaded
            Resource tmpR;
            Texture tmpT;
            if(rMap.containsKey(textureFiles[i].getParent()))
            {                
                tmpR = (Resource)rMap.get(textureFiles[i].getParent());
                String textureFileType = textureFiles[i].getName().split(".")[1];
                try {
                    tmpT = TextureLoader.getTexture(textureFileType,ResourceLoader.getResourceAsStream(textureFiles[i].getPath()));
                    String textureName = textureFiles[i].getName().split(".")[0];
                    tmpR.addTexture(textureName,tmpT);
                } catch (IOException ex) {
                    System.err.println("Could not load texture "+textureFiles[i].getName());
                }
            }
            else
            {
                //Add the new Resource
                rMap.put(textureFiles[i].getParent(),new Resource(textureFiles[i].getParent()));
                //Do Stuff
                tmpR = (Resource)rMap.get(textureFiles[i].getParent());                
                String textureFileType = textureFiles[i].getName().split(".")[1];
                try {
                    tmpT = TextureLoader.getTexture(textureFileType,ResourceLoader.getResourceAsStream(textureFiles[i].getPath()));
                    String textureName = textureFiles[i].getName().split(".")[0];
                    tmpR.addTexture(textureName,tmpT);
                } catch (IOException ex) {
                    System.err.println("Could not load texture "+textureFiles[i].getName());
                }
            }
        }
       
        //load Audio Files
       File[] audioFiles = listFilesAsArray(new File("assets"),new FilenameFilter() {

       @Override
       public boolean accept(File dir, String name) {
            return name.endsWith("ogg");
            }
            },true);
        for(int i = 0; i<audioFiles.length; i++)
        {
            //if the other assets from the resource have already been loaded
            Resource tmpR;
            Audio tmpA;
            if(rMap.containsKey(audioFiles[i].getParent()))
            {                
                tmpR = (Resource)rMap.get(audioFiles[i].getParent());
                String audioFileType = audioFiles[i].getName().split(".")[1];
                try {
                    tmpA = AudioLoader.getAudio(audioFileType,ResourceLoader.getResourceAsStream(audioFiles[i].getPath()));
                    String audioName = audioFiles[i].getName().split(".")[0];
                    tmpR.addSound(audioName,tmpA);
                } catch (IOException ex) {
                    System.err.println("Could not load sound "+audioFiles[i].getName());
                }
            }
            else
            {
                //Add the new Resource
                rMap.put(audioFiles[i].getParent(),new Resource(audioFiles[i].getParent()));
                //Do Stuff
                tmpR = (Resource)rMap.get(audioFiles[i].getParent());                
                String audioFileType = audioFiles[i].getName().split(".")[1];
                try {
                    tmpA = AudioLoader.getAudio(audioFileType,ResourceLoader.getResourceAsStream(audioFiles[i].getPath()));
                    String audioName = audioFiles[i].getName().split(".")[0];
                    tmpR.addSound(audioName,tmpA);
                } catch (IOException ex) {
                    System.err.println("Could not load sound "+audioFiles[i].getName());
                }
            }
        }
    }
    public Resource getResource(String name)
    {
        return (Resource)rMap.get(name);
    }
private File[] listFilesAsArray(
		File directory,
		FilenameFilter filter,
		boolean recurse)
{
	Collection<File> files = listFiles(directory,
			filter, recurse);
//Java4: Collection files = listFiles(directory, filter, recurse);
	
	File[] arr = new File[files.size()];
	return files.toArray(arr);
}

private Collection<File> listFiles(
// Java4: public static Collection listFiles(
		File directory,
		FilenameFilter filter,
		boolean recurse)
{
	// List of files / directories
	Vector<File> files = new Vector<File>();
// Java4: Vector files = new Vector();
	
	// Get files / directories in the directory
	File[] entries = directory.listFiles();
	
	// Go over entries
	for (File entry : entries)
	{
// Java4: for (int f = 0; f < files.length; f++) {
// Java4: 	File entry = (File) files[f];

		// If there is no filter or the filter accepts the 
		// file / directory, add it to the list
		if (filter == null || filter.accept(directory, entry.getName()))
		{
			files.add(entry);
		}
		
		// If the file is a directory and the recurse flag
		// is set, recurse into the directory
		if (recurse && entry.isDirectory())
		{
			files.addAll(listFiles(entry, filter, recurse));
		}
	}
	
	// Return collection of files
	return files;
}
}
