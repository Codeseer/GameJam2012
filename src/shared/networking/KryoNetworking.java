/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serialize.CollectionSerializer;
import com.esotericsoftware.kryo.serialize.FieldSerializer;
import java.util.ArrayList;

/**
 *
 * @author Scott Adams
 */
public class KryoNetworking 
{
    public KryoNetworking(Kryo kryo)
    {
            kryo.register(UpdateRequest.class,new FieldSerializer(kryo, UpdateRequest.class));
            kryo.register(ServerRequest.class,new FieldSerializer(kryo, ServerRequest.class));
            kryo.register(GameObject.class, new FieldSerializer(kryo, GameObject.class));
            kryo.register(ArrayList.class, new CollectionSerializer(kryo));
            kryo.register(Movement.class, new FieldSerializer(kryo, Movement.class));
            kryo.register(PlayerObject.class, new FieldSerializer(kryo, PlayerObject.class));
    }
}
