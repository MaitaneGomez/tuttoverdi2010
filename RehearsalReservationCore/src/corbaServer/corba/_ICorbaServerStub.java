package corbaServer.corba;


/**
* corbaServer/corba/_ICorbaServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* martes 13 de abril de 2010 09H43' CEST
*/

public class _ICorbaServerStub extends org.omg.CORBA.portable.ObjectImpl implements corbaServer.corba.ICorbaServer
{

  public corbaServer.corba.corbaServerRehearsalDTO[] getRehearsals ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRehearsals", true);
                $in = _invoke ($out);
                corbaServer.corba.corbaServerRehearsalDTO $result[] = corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRehearsals (        );
            } finally {
                _releaseReply ($in);
            }
  } // getRehearsals

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corbaServer/corba/ICorbaServer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ICorbaServerStub
