import threading
import socket
import matplotlib.pyplot as plt


# check if a port is open
def check_port(host, port):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        s.connect((host, port))
        s.shutdown(2)
        print(f"Port {port} is open")
        return True
    except:
        print(f"Port {port} is closed")
        return False
    finally:
        s.close()


# function to be run by each thread
def thread_function(ip, port_init, port_end, open_ports):
    port_count = 0
    thread_name = threading.current_thread().name

    for port in range(port_init, port_end):
        if check_port(ip, port):
            port_count += 1

    open_ports[thread_name] = port_count


def main():
    # number of threads
    n_threads = 4
    # ip address
    ip = "localhost"
    # list of open ports
    open_ports = {}
    # list of threads
    threads = []

    # ports to be analyzed and the number of ports to be analyzed by each thread
    port_range = range(50, 451)
    step = len(port_range) // n_threads
    ranges = [(i, i + step) for i in range(50, 451, step)]

    # create the threads
    for i, (port_init, port_end) in enumerate(ranges):
        threads.append(
            threading.Thread(target=thread_function, args=(ip, port_init, port_end, open_ports), name=f"Thread {i}")
        )

    # start the threads
    for thread in threads:
        thread.start()

    # wait for the threads to finish
    for thread in threads:
        thread.join()

    print(f"Open ports: {open_ports}")

    # plot the dictionary of open ports
    plt.bar(open_ports.keys(), open_ports.values())
    plt.show()


if __name__ == "__main__":
    main()
