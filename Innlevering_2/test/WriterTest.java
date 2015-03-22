import org.easymock.*;
import org.junit.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.easymock.EasyMock.*;

public class WriterTest {
    private Client.Writer writer;

    @Mock
    private DataOutputStream mockOut;
    @Mock
    private Scanner mockIn;
    @Mock
    private Socket mockSocket;


    @Before
    public void setUp() throws Exception {
        mockSocket = new Socket();
        mockIn = new Scanner(new InputStreamReader(System.in));
        mockOut = new DataOutputStream(mockSocket.getOutputStream());
    }

    @Test
    public void testRun() throws Exception {
        String message = "message";
        expect(mockIn.nextLine()).andReturn(message).anyTimes().andDelegateTo(mockOut);
        verify(mockIn, mockOut);
    }

    @Test
    public void testClose() throws Exception {

    }
}