import socket
import threading
import matplotlib.pyplot as plt

NUM_THREADS = 4
PORT_RANGE = range(50, 451, 100)


def check_port(port):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(1)
    result = sock.connect_ex(('localhost', port))
    sock.close()
    return result == 0


def thread_function(start_port, end_port, result_list):
    open_ports = []
    for port in range(start_port, end_port):
        if check_port(port):
            open_ports.append(port)
    result_list.append(len(open_ports))


if __name__ == '_main_':
    threads = []
    results = []
    for i in range(NUM_THREADS):
        start_port = PORT_RANGE[i]
        end_port = PORT_RANGE[i+1]
        result_list = []
        thread = threading.Thread(target=thread_function, args=(start_port, end_port, result_list))
        threads.append(thread)
        results.append(result_list)
        thread.start()

    for thread in threads:
        thread.join()

    # Plot the results
    plt.bar(range(NUM_THREADS), [len(result) for result in results])
    plt.xlabel('Thread')
    plt.ylabel('Open Ports')
    plt.show()
