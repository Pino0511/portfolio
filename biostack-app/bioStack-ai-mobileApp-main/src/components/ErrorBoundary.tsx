import { Component, type ErrorInfo, type ReactNode } from 'react';
import { AlertCircle, WifiOff, RefreshCw } from 'lucide-react';

interface Props {
  children: ReactNode;
}

interface State {
  hasError: boolean;
  isOffline: boolean;
  error: Error | null;
}

class ErrorBoundary extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { hasError: false, isOffline: !navigator.onLine, error: null };
  }

  componentDidMount() {
    window.addEventListener('online', this.handleOnline);
    window.addEventListener('offline', this.handleOffline);
  }

  componentWillUnmount() {
    window.removeEventListener('online', this.handleOnline);
    window.removeEventListener('offline', this.handleOffline);
  }

  handleOnline = () => this.setState({ isOffline: false });
  handleOffline = () => this.setState({ isOffline: true });

  static getDerivedStateFromError(error: Error): State {
    return { hasError: true, isOffline: false, error };
  }

  componentDidCatch(_error: Error, _errorInfo: ErrorInfo) {
    // production monitoring hook
  }

  handleRetry = () => {
    this.setState({ hasError: false, error: null });
    window.location.reload();
  };

  render() {
    if (this.state.isOffline) {
      return (
        <div className="flex min-h-[100dvh] items-center justify-center bg-canvas px-6 text-center dark:bg-ink-950">
          <div className="max-w-sm">
            <WifiOff className="mx-auto h-10 w-10 text-ink-400" />
            <h1 className="mt-4 text-xl font-semibold">Sei offline</h1>
            <p className="mt-2 text-sm text-ink-500">
              BioStack funziona in locale, ma alcune risorse (font/Amazon) richiedono connessione.
            </p>
          </div>
        </div>
      );
    }

    if (this.state.hasError) {
      return (
        <div className="flex min-h-[100dvh] items-center justify-center bg-canvas px-6 text-center dark:bg-ink-950">
          <div className="max-w-sm">
            <AlertCircle className="mx-auto h-10 w-10 text-rose-600" />
            <h1 className="mt-4 text-xl font-semibold">Qualcosa non ha funzionato</h1>
            <p className="mt-2 text-sm text-ink-500">
              Riprova. I tuoi dati locali non vengono cancellati da questo errore.
            </p>
            <button
              type="button"
              onClick={this.handleRetry}
              className="mt-6 inline-flex h-12 items-center justify-center gap-2 rounded-2xl bg-forest-700 px-5 text-sm font-semibold text-white dark:bg-moss-400 dark:text-ink-950"
            >
              <RefreshCw className="h-4 w-4" /> Riprova
            </button>
          </div>
        </div>
      );
    }

    return this.props.children;
  }
}

export default ErrorBoundary;
